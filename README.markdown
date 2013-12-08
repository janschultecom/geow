Geow Openstreetmap API
======================

Geow is a simple openstreetmap API designed specifically designed for streaming and processing openstreetmap data.


#Installation
Building from Source

        $ mvn install

#Getting started

## Streaming
Geow allows you to easily stream osm files in xml format. 
```
streamer = new OsmObjectStreamer(osmXmlFile);
streamer.open();
while (streamer.hasNext()) {
        
	IOsmObject element = streamer.next();
	if (element instanceof IOsmNode) {
		IOsmNode node = (IOsmNode) element;
		// Do stuff with node
		
	} else if (element instanceof IOsmWay) {
		IOsmWay way = (IOsmWay) element;
		// Do stuff with way
		
	} else if (element instanceof IOsmRelation) {
		IOsmRelation rel = (IOsmRelation) element;
		// Do stuff with relation
		
	} 
}
streamer.close();
```
#Documentation

Documentation and tutorials can be found on the [Geow wiki](http://github.com/jansonhanson/geow/wiki).
