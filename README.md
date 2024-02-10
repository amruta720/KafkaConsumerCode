The Problem: Message is being produced that contains device_id and a upc for the product scanned for billing. 
Aggregate group by device_id and produce a list of top five keys along with their count. key should be device id.


This code iterates over the aggregated data stored in the aggregatedData map. 
For each device ID (deviceId) in the map, it retrieves the associated counts of UPCs. 
It then sorts these counts in descending order, selects the top five UPCs with the highest counts, and stores them in the topFiveUPCs list.
This is achieved using Java Streams, where the entrySet() method is used to get a set of entries (key-value pairs) from the upcCounts map. 
The entries are then sorted based on the counts of UPCs (getValue()), limiting the stream to the top five entries. 
Finally, the results are printed or further processed, with each device ID and its corresponding top five UPCs being displayed. 
This code effectively identifies and presents the top five UPCs associated with each device ID in the aggregated data.




