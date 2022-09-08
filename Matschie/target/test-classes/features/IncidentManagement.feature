Feature: Incident Management in the Service now application

Scenario: TC001 Create a Incident without any body in the HTTP request
Given user set the queryparam in the Incident management request
	|sysparm_fields	|sys_id, description, category, number|
And user set the request header as 'Json' in the Incident management request
When user sends a Post request to the service now application
Then Verify the status code 201 for the Incident management response
And Validate the sys_id in the Incident management response


Scenario Outline: TC002 Create a Incident with body as file in the HTTP request
Given user set the queryparam in the Incident management request
	|sysparm_fields	|sys_id, description, category, number|
And user set the request header as <Content-Type> in the Incident management request
And user set the request body filename as <File_Name> in the Incident management request
When user sends a Post request to the service now application
Then Verify the status code 201 for the Incident management response
And Validate the sys_id in the Incident management response

Examples:
|File_Name							|Content-Type		|
|'CreateIncident.json'	|'Json'					|
|'CreateIncident.xml'		|'XML'					|


Scenario: TC003 Get a Incident with default response type
Given user set the queryparam in the Incident management request
|sysparm_fields	|sys_id, description, category, number|
When user sends a Get request to the service now application
Then Verify the status code 200 for the Incident management response

Scenario: TC004 Get a Incident with filter category
Given user set the queryparam in the Incident management request
	|sysparm_fields	|sys_id, description, category, number|
	|category				|software															|
When user sends a Get request to the service now application
Then Verify the status code 200 for the Incident management response

