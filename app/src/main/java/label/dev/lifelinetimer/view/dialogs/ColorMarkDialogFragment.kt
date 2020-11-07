package label.dev.lifelinetimer.view.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.fragment.DialogFragmentNavigator
import kotlinx.android.synthetic.main.colormark_dialogfragment.view.*
import label.dev.lifelinetimer.R
import label.dev.lifelinetimer.model.models.dbmodels.notes.ColorMarks

class ColorMarkDialogFragment : DialogFragment() {

    var onColorSelected: ((String) -> Unit?)? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.colormark_dialogfragment, container, false)



        view.whiteCM.setOnClickListener {
            val color = ColorMarks.WHITE.name
            onColorSelected?.invoke(color)
            dismiss()
        }
        view.greenCM.setOnClickListener {
            val color = ColorMarks.GREEN.name
            onColorSelected?.invoke(color)
            dismiss()
        }
        view.yellowCM.setOnClickListener {
            val color = ColorMarks.YELLOW.name
            onColorSelected?.invoke(color)
            dismiss()
        }
        view.blueCM.setOnClickListener {
            val color = ColorMarks.BLUE.name
            onColorSelected?.invoke(color)
            dismiss()
        }
        view.redCM.setOnClickListener {
            val color = ColorMarks.RED.name
            onColorSelected?.invoke(color)
            dismiss()
        }
        view.purpleCM.setOnClickListener {
            val color = ColorMarks.PURPLE.name
            onColorSelected?.invoke(color)
            dismiss()
        }

        view.cancleCMDialogViewBTN.setOnClickListener {

            dismiss()
        }

        return view
    }
}