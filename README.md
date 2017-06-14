# running-info-service
REST API for CRUD operation using mysql
A database with name "myDB" created in mysql with port 3306, the application is at port 9000

The REST API support operations of:
Upload, using POST at path: localhost:9000/runninginfo, taking JSON input
Delete all, using POST at path: localhost:9000/purge
Delete by runningId, using POST at path: localhost:9000/delete/{id}
Sort by healthWarningLevel, paging with 2 element per page, using GET at path: localhost:9000/search/pagingByWarning?&page=n
if two or more elements have same healthWarningLevel, will be sorted based on heartRate with descending order.

I haven't been able to create an independent Json output object from the data to fullfil the requirement of Json response.
