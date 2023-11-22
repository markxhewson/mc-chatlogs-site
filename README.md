# mc-chatlogs-site
This system uses three different components, a Minecraft plugin, an express API, and a React + Vite web page.

This is a moderation system for server owners to view Minecraft chat logs for their users in an effective way.

The only setup required is to fill in the config options for both the plugin and the API, run them, then the web page will automatically load all messages from the database as they come in for each user.

<h1>Requirements</h1>
<li> A host (preferably a VPS) where you can host your web page, API, and database.</li>
<li>A Minecraft server</li>
<li>Understanding of how to host web pages and API's</li>
<li>Understanding of how to set up a database</li>

<h1>Web Page</h1>
<h4>Search for messages by user UUID</h4>
<img src="https://i.imgur.com/VB0aEEW.png"/>

<h1>Plugin Configuration</h1>

```yml
database:
  username: ''
  password: ''
  address: ''
  database: 'chatlog'
  port: 3306
server:
  name: 'server'
```

<h1>API Configuration</h1>

```json
{
  "database": {
    "username": "",
    "password": "",
    "address": "",
    "database": "chatlog",
    "port": 3306,
    "dialect": "mysql"
  }
}
```
