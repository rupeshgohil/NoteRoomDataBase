package zestbrains.stintr.listener

import android.view.View
import gohil.aru.noteroomdatabase.modal.Note


interface MultiClickListener {

   open fun onMultiClick(pos: Int,note : Note,str:String)

}
