# NoteRoomDataBase
Room DataBase
CRUD operation with room datadase
This is a demo app on how to implement Room persistance library, making use of LiveData in Android app</br></br>

<img src="https://github.com/rupeshgohil/NoteRoomDataBase/blob/master/screen/1.png" width="200" style="max-width:100%;"> 
  <img src="https://github.com/rupeshgohil/NoteRoomDataBase/blob/master/screen/2.png" width="200" style="max-width:100%;"> 
  <img src="https://github.com/rupeshgohil/NoteRoomDataBase/blob/master/screen/3.png" width="200" style="max-width:100%;">

<h3>How to implement Room: a SQLite object mapping library in your Android app?</h3>
Step 1: Add following library and annotation processor to your app gradle file.

```
compile "android.arch.persistence.room:runtime:1.0.0"
annotationProcessor "android.arch.persistence.room:compiler:1.0.0" 
```
</br>

<b>Note:</b> The reason why annotation processor is needed is because all operations like Insert, Delete, Update etc are annotated.</br></br>
