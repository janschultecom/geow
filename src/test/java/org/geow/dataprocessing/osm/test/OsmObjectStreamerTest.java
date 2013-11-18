package org.geow.dataprocessing.osm.test;

import java.net.URL;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.geow.dataprocessing.StreamException;
import org.geow.dataprocessing.osm.Bounds;
import org.geow.dataprocessing.osm.Node;
import org.geow.dataprocessing.osm.OsmObjectStreamer;
import org.geow.dataprocessing.osm.Relation;
import org.geow.dataprocessing.osm.Way;
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
	private URL inputFile;

	@Before
	public void setUp() throws StreamException {
		inputFile = OsmObjectStreamerTest.class.getClassLoader().getResource(
				"germany.nrw.dusseldorf.center.osm");
		streamer = new OsmObjectStreamer(inputFile);
	}

	@Test
	public void testOsmObjectStreamer() throws StreamException {
			streamer.open();
			while (streamer.hasNext()) {
				IOsmObject element = streamer.next();
				if (element instanceof Node) {
					IOsmNode node = ((IOsmNode) element);
					logger.trace("Streamer returned node {}",node);
				} else if (element instanceof Way) {
					IOsmWay way = ((IOsmWay) element);
					logger.trace("Streamer returned way {}",way);
				} else if (element instanceof Relation) {
					IOsmRelation rel = ((IOsmRelation) element);
					logger.trace("Streamer returned way {}",rel);
				} else if (element instanceof Bounds) {
					IOsmBounds bounds = ((IOsmBounds) element);
					logger.trace("Streamer returned way {}",bounds);
				}else{
					logger.error("Streamer couldnt parse element: "+element+"\nClazz: "+element.getClass().getSimpleName());
				}

			}
			streamer.close();
	}

	@Test
	public void testOpenClose() throws StreamException {
		streamer.open();
		streamer.restart();
		streamer.close();
	}

}
