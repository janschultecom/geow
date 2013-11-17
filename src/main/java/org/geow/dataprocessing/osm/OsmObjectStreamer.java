/*******************************************************************************
 * Copyright 2013 Jan Schulte
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
package org.geow.dataprocessing.osm;

import static javax.xml.stream.XMLStreamConstants.CHARACTERS;
import static javax.xml.stream.XMLStreamConstants.START_ELEMENT;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.FactoryConfigurationError;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.geow.dataprocessing.IObjectStreamer;
import org.geow.dataprocessing.StreamException;
import org.geow.model.IOsmObject;

public class OsmObjectStreamer implements IObjectStreamer<IOsmObject> {
	private static Logger logger = LogManager.getLogger(OsmObjectStreamer.class
			.getName());

	private JAXBContext jaxbContext = null;
	private XMLStreamReader xmlStreamReader = null;
	private Unmarshaller unmarshaller = null;
	private URL inputFile = null;
	private InputStream stream = null;

	public OsmObjectStreamer(URL inputFile) throws StreamException {
		try {
			jaxbContext = JAXBContext.newInstance(Osm.class, Way.class,
					Node.class, Bounds.class, Member.class, Nd.class,
					Relation.class, Tag.class);
			unmarshaller = jaxbContext.createUnmarshaller();
			this.inputFile = inputFile;
		} catch (JAXBException e) {
			throw new StreamException("Could not create Unmarshaller", e);
		}
	}

	public boolean hasNext() throws StreamException {
		if (xmlStreamReader != null) {
			try {
				while (xmlStreamReader.hasNext()) {
					if (xmlStreamReader.getEventType() == CHARACTERS) {
						xmlStreamReader.next();
						continue;
					}
					String name = xmlStreamReader.getLocalName();
					if (xmlStreamReader.getEventType() == START_ELEMENT
							&& (name.equals("node") || name.equals("way")
									|| name.equals("relation") || name
										.equals("bounds"))) {
						logger.trace("Streamer has more objects");
						return true;
					}
					xmlStreamReader.next();
				}
			} catch (XMLStreamException e) {
				throw new StreamException(
						"Could not calculate whether stream has next object", e);
			}

		}
		logger.trace("Streamer has no more objects");
		return false;

	}

	public IOsmObject next() throws StreamException {
		IOsmObject element = null;
		try {
			xmlStreamReader.require(START_ELEMENT, null, null);
			String name = xmlStreamReader.getLocalName();
			if (name.equals("node")) {
				element = (Node) unmarshaller.unmarshal(xmlStreamReader);
				logger.trace("Unmarshalling node.");
			} else if (name.equals("way")) {
				element = (Way) unmarshaller.unmarshal(xmlStreamReader);
				logger.trace("Unmarshalling way.");
			} else if (name.equals("relation")) {
				element = (Relation) unmarshaller.unmarshal(xmlStreamReader);
				logger.trace("Unmarshalling relation.");
			} else if (name.equals("bounds")) {
				element = (Bounds) unmarshaller.unmarshal(xmlStreamReader);
				logger.trace("Unmarshalling bounds.");
			}else{
				logger.error("Could not marshall element "+name);
			}

			logger.trace("Object properties are {} ", element);

			if (xmlStreamReader.getEventType() == CHARACTERS) {
				xmlStreamReader.next();
			}
		} catch (XMLStreamException | JAXBException e) {
			throw new StreamException("Failure while unmarshalling element", e);
		}
		return element;
	}

	@Override
	public void open() throws StreamException {
		logger.debug("Opening input stream.");
		try {

			//stream = inputFile.openStream();
			stream = new BufferedInputStream(new FileInputStream(inputFile.getFile()));
			xmlStreamReader = XMLInputFactory.newInstance()
					.createXMLStreamReader(stream);
			xmlStreamReader.nextTag();
			xmlStreamReader.require(START_ELEMENT, null, "osm");
			xmlStreamReader.nextTag();
		} catch (XMLStreamException | FactoryConfigurationError | IOException e) {
			throw new StreamException("Failure while loading inputstream ", e);
		}

	}

	@Override
	public void close() throws StreamException {
		logger.debug("Closing input stream.");
		try {
			xmlStreamReader.close();
		} catch (XMLStreamException e) {
			throw new StreamException("Failure while closing stream reader ", e);
		}
		try {

			stream.close();
		} catch (IOException e) {
			throw new StreamException("Failure while closing input stream ", e);
		}

	}

	@Override
	public void restart() throws StreamException {
		logger.debug("Restarting input stream.");
		close();
		open();
	}

}
