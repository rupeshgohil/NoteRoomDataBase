# NoteRoomDataBase
Room DataBase
CRUD operation with room datadase
# How to implement Room: a SQLite object mapping library in your Android app?
Add following library and annotation processor to your app gradle file.

compile "android.arch.persistence.room:runtime:1.0.0"
annotationProcessor "android.arch.persistence.room:compiler:1.0.0" 

Note: The reason why annotation processor is needed is because all operations like Insert, Delete, Update etc are annotated.
