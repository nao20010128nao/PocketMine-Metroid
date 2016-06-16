package net.pocketmine.forum;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.LinearLayout;
import com.astuetz.PagerSlidingTabStrip;
import com.nao20010128nao.PM_Metroid.R;
import java.util.ArrayList;
import net.pocketmine.forum.PluginsActivity.Plugin;

public class CategoryActivity extends AppCompatActivity {

	int cat;
	PagerSlidingTabStrip tabs;
	ViewPager pager;
	PluginsTabs adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_category);

		cat = getIntent().getIntExtra("category", -1);
		if (cat == -1) {
			Log.e("CategoryActivity",
					"Finishing activity, because no category given.");
			finish();
			return;
		}

		String catName = getIntent().getStringExtra("title");
		if (catName != null) {
			getSupportActionBar().setTitle(catName);
		}

		ActionBar bar = getSupportActionBar();
		bar.setDisplayShowHomeEnabled(true);
		bar.setDisplayHomeAsUpEnabled(true);
		bar.setElevation(0);

		tabs = (PagerSlidingTabStrip) findViewById(R.id.plugins_tabs);
		pager = (ViewPager) findViewById(R.id.plugins_pager);
		adapter = new PluginsTabs(getSupportFragmentManager());
		pager.setAdapter(adapter);
		tabs.setViewPager(pager);
		PluginsActivity.setupPSTS(tabs);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.category, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == android.R.id.home) {
			finish();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	public class PluginsTabs extends FragmentPagerAdapter {

		public String[] tabs = getResources().getStringArray(R.array.forum_categories);

		public PluginsTabs(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			if (position == 0) {
				return createFragment(0);
			} else if (position == 1) {
				return createFragment(1);
			} else if (position == 2) {
				return createFragment(2);
			} else if (position == 3) {
				return createFragment(3);
			} else if (position == 4) {
				return createFragment(4);
			}
			return new ListFragment();
		}

		private ListFragment createFragment(int id) {
			ListFragment fragment = new ListFragment();
			Bundle args = new Bundle();
			args.putInt("cat", cat);
			args.putInt("tab", id);
			fragment.setArguments(args);

			return fragment;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			return tabs[position];
		}

		@Override
		public int getCount() {
			return tabs.length;
		}

	}

	public static class ListFragment extends Fragment {

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {

			int id = getArguments().getInt("tab", 0);
			int cat = getArguments().getInt("cat", 0);
			ArrayList<Plugin> plugins = null;

			if (id == 0) {
				plugins = PluginsActivity.featuredPlugins;
			} else if (id == 1) {
				plugins = PluginsActivity.bestRated;
			} else if (id == 2) {
				plugins = PluginsActivity.topPlugins;
			} else if (id == 3) {
				plugins = PluginsActivity.topNewPlugins;
			} else if (id == 4) {
				plugins = PluginsActivity.recentlyUpdated;
			}

			if (plugins == null) {
				return new LinearLayout(getActivity()); // whatever
			}

			ArrayList<Plugin> categorizedPlugins = new ArrayList<PluginsActivity.Plugin>();

			for (Plugin p : plugins) {
				if (p.category == cat) {
					categorizedPlugins.add(p);
				}
			}

			View v = inflater.inflate(R.layout.activity_plugins_list,
					container, false);
			GridView grid = (GridView) v.findViewById(R.id.plugins_list);
			grid.setSelector(new ColorDrawable(Color.TRANSPARENT));
			grid.setAdapter(new GridAdapter(getActivity(), categorizedPlugins));

			return v;
		}

	}

}
