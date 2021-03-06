package net.pocketmine.server.log.buttons;
import android.content.DialogInterface;
import com.nao20010128nao.PM_Metroid.R;
import com.nao20010128nao.Wisecraft.misc.compat.AppCompatAlertDialog;
import net.pocketmine.server.LogActivity;

public class Op extends NameSelectAction {
	public Op(LogActivity a) {
		super(a);
	}

	@Override
	public void onSelected(final String s) {
		// TODO: Implement this method
		new AppCompatAlertDialog.Builder(this,R.style.AppAlertDialog)
			.setMessage(getResString(R.string.giveOpAsk).replace("[PLAYER]", s))
			.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener(){
				public void onClick(DialogInterface di, int w) {
					getActivity().performSend("op " + s);
				}
			})
			.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener(){
				public void onClick(DialogInterface di, int w) {}
			})
			.show();
	}

	@Override
	public int getViewId() {
		// TODO: Implement this method
		return R.id.op;
	}
}
