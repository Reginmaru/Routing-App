v0.0

1. Creating directories for routeXML, link and node. Thinking of the next steps... Construct link and node classes first. 
2. They need to be instantiated so can't use abstract classes.
3. Haven't yet learned how to attach one - many on java so gonna look that up after I create classes.

v0.01

1. created both the link and node classes. At first I tried using node.Name() as a return value but I didn't want to mess with the code at this point. I kept them as String and I will add exceptions to routeXML if they don't exist.
2. Added routeXML class basics.
3. Gonna search of the class to xml and xml to class.

v0.02

1. I want to create an algorthim to "link" links if you will to react a chain. So I am going to react another class that creates chains. P.S. I've kept all the java files in lower case as the xml file had then in lower.
2. Just thinking on the plane... I need to loop through get(0) with get(1) of link to "match", then create a longer chain? or I could create a matrix of starting vs ending and use those positions to form link?