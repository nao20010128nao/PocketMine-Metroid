package net.pocketmine.server.log.buttons;
import android.content.DialogInterface;
import com.nao20010128nao.PM_Metroid.R;
import com.nao20010128nao.Wisecraft.misc.compat.AppCompatAlertDialog;
import net.pocketmine.server.LogActivity;
import net.pocketmine.server.Utils.Constant;

public class DefaultGamemode extends NameSelectAction {
	public DefaultGamemode(LogActivity a) {
		super(a);
	}

	@Override
	public void onSelected(final String s) {
		// TODO: Implement this method
		new AppCompatAlertDialog.Builder(this,R.style.AppAlertDialog)
			.setMessage(getResString(R.string.defaultgamemodeAsk).replace("[MODE]", s))
			.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener(){
				public void onClick(DialogInterface di, int w) {
					getActivity().performSend("defaultgamemode " + s);
				}
			})
			.setNegativeButton(android.R.string.cancel, Constant.BLANK_DIALOG_CLICK_LISTENER)
			.show();
	}

	@Override
	public int getViewId() {
		// TODO: Implement this method
		return R.id.defaultgamemode;
	}

	@Override
	public String[] onPlayersList() {
		// TODO: Implement this method
		return getResources().getStringArray(R.array.defaultgamemodeConst);
	}
}
