# mc-chatlogs-site
A 3-piece bit of software including; api, site and plugin that tracks minecraft chat and displays it in a web page.

This system starts with a Minecraft plugin, which is where collects user messages and stores them in a database with the required information:
- id
- uuid
- message
- timestamp
- server

An API is then used to collect the data from the database and display it in JSON format, for the site to then retrieve and display.

<h1>Web Page</h1>
<h4>Search for messages by user UUID</h4>
![image](https://prnt.sc/pM8JMImPSEFl)
