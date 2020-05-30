# notes-service
notes service

application adds three users on startup and adds one note for user with email - `userone@demo.com`

###curl commands - 

#### 1. get notes for userone 
curl -i --user userone@demo.com:password1 http://localhost:8081/notes 

`[
   {
     "id": 1,
     "title": "note title 1",
     "description": "note description 1",
     "created": "2020-05-29T23:34:01",
     "updated": "2020-05-29T16:34:01"
   }
 ]`
 
 #### 2. add new note for userone 
 curl -X POST --user userone@demo.com:password1 -H "Content-Type: application/json" -d '{
         "title":"second note",
         "description":"second note description"
  }' "http://localhost:8081/notes"
  
  `{
      "id" : 2,
      "title" : "second note",
      "description" : "second note description",
      "created" : "2020-05-30T09:27:59.106827",
      "updated" : null
   }`

#### 3. delete a new note   
curl --user userone@demo.com:password1 -X DELETE "http://localhost:8081/notes/3"
