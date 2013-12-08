package org.geow.dataprocessing.osm.test;

import java.io.File;
import java.net.URL;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.geow.dataprocessing.StreamException;
import org.geow.dataprocessing.osm.OsmObjectStreamer;
import org.geow.model.IOsmBounds;
import org.geow.model.IOsmNode;
import org.geow.model.IOsmObject;
import org.geow.model.IOsmRelation;
import org.geow.model.IOsmWay;
import org.junit.Before;
import org.junit.Test;

public class OsmObjectStreamerTest {
	private static Logger logger = LogManager.getLogger(OsmObjectStreamerTest.class
			.getName());

	private OsmObjectStreamer streamer;

	@Before
	public void setUp() throws StreamException {
		/**
		 * Download your .osm file into the test/resources folder in order to perform test. 
		 */
		URL path = OsmObjectStreamerTest.class.getClassLoader().getResource(
				"tinytest.osm");
		if(path != null){
			File inputFile = new File(path.getFile());
			streamer = new OsmObjectStreamer(inputFile);
		}
	}

	@Test
	public void testOsmObjectStreamer() throws StreamException {
			if(streamer != null){
				streamer.open();
				while (streamer.hasNext()) {
					IOsmObject element = streamer.next();
					if (element instanceof IOsmNode) {
						IOsmNode node = (IOsmNode) element;
						logger.trace("Streamer returned {}",node);
					} else if (element instanceof IOsmWay) {
						IOsmWay way = (IOsmWay) element;
						logger.trace("Streamer returned {}",way);
					} else if (element instanceof IOsmRelation) {
						IOsmRelation rel = (IOsmRelation) element;
						logger.trace("Streamer returned {}",rel);
					} else if (element instanceof IOsmBounds) {
						IOsmBounds bounds = (IOsmBounds) element;
						logger.trace("Streamer returned {}",bounds);
					}else{
						logger.error("Streamer couldnt parse element: "+element+"\nClazz: "+element.getClass().getSimpleName());
					}
	
				}
				streamer.close();
			}
	}

	@Test
	public void testOpenClose() throws StreamException {
		if(streamer != null){
			streamer.open();
			streamer.restart();
			streamer.close();
		}
	}

}
