package aimax.osm.data;

import java.util.List;

import aimax.osm.data.entities.EntityAttribute;
import aimax.osm.data.entities.EntityViewInfo;
import aimax.osm.data.entities.MapNode;
import aimax.osm.data.entities.MapWay;

/**
 * Simple interface to aggregate map data. Its purpose is, to keep OSM parsers
 * independent from the concrete structure which is used to store the data. Osm
 * maps should only be modified by implementations of this map builder interface.
 * 
 * @author Ruediger Lunde
 */
public interface MapBuilder {

	/**
	 * Provides the builder with an entity classifier. The classifier defines
	 * the scale-dependent visibility of entities and by that strongly
	 * influences the organization of the data.
	 */
	public void setEntityClassifier(EntityClassifier<EntityViewInfo> classifier);

	/** Defines the region in which complete map data is available. */
	public void setBoundingBox(BoundingBox bb);

	/**
	 * Returns a node for the given id if such a node was added before.
	 */
	public MapNode getNode(long id);

	/**
	 * Adds a new map node (way node as well as point of interest) to the
	 * container.
	 */
	public void addNode(long id, String name, List<EntityAttribute> atts,
			float lat, float lon);

	/**
	 * Returns a way for the given id if such a way was added before.
	 */
	public MapWay getWay(long id);

	/**
	 * Adds a new map way to the container, adds all referenced way nodes to the
	 * container (without position) if they have not been added before, stores
	 * the list of way nodes with the way, and additionally adds a way reference
	 * to each node. The way nodes define the representation of the way at zoom
	 * level 0. The missing positions of the nodes may be set later on.
	 */
	public void addWay(long id, String name, List<EntityAttribute> atts,
			List<Long> wayNodeIds);

	/**
	 * Checks whether node references without corresponding node definitions
	 * have been added since the last call.
	 */
	public boolean nodeRefsWithoutDefsAdded();

	/**
	 * Returns the modified or created map. This method must be called after all
	 * map entities have been added.
	 */
	public OsmMap buildMap();
}