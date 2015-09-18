package com.yang.file_explorer.utils;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.actionbarsherlock.view.MenuItem.OnMenuItemClickListener;
import com.actionbarsherlock.view.SubMenu;
import com.yang.file_explorer.R;
import com.yang.file_explorer.apis.FileInteractionHub;
import com.yang.file_explorer.entity.GlobalConsts;
import com.yang.file_explorer.ui.MainActivity;
import com.yang.file_explorer.ui.SearchActivity;

public class MenuUtils implements OnMenuItemClickListener {

	private Context mContext;
	private FileInteractionHub mFileInteractionHub;

	public enum MenuItemType {
		MENU_DEVICE, MENU_FAVORITE, MENU_WIFI, MENU_MUSIC, MENU_IMAGE, MENU_VIDEO, MENU_DOCUMENT, MENU_ZIP, MENU_APK
	}

	private static MenuUtils mmenuMenuUtils = null;

	// ���캯��
	public MenuUtils(Context context, FileInteractionHub fileInteractionHub) {
		mContext = context;
		mFileInteractionHub = fileInteractionHub;
	}
	
	public boolean addMenu(Menu menu) {

		// ����˵�
		SubMenu subMenu = menu.addSubMenu(0, 1, 0, R.string.menu_item_sort)
				.setIcon(R.drawable.ic_sort_actionbar);
		subMenu.getItem().setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);

		addMenu(subMenu, 11, 0, R.string.menu_item_sort_name);
		addMenu(subMenu, 12, 1, R.string.menu_item_sort_date);
		addMenu(subMenu, 13, 2, R.string.menu_item_sort_size);
		addMenu(subMenu, 14, 3, R.string.menu_item_sort_type);
		subMenu.setGroupCheckable(0, true, true);
		subMenu.getItem(0).setChecked(true);

		// �½��˵�
		addMenu(menu, 2, 1, R.string.new_folder_name,
				R.drawable.ic_create_actionbar);

		// �����˵�
		addMenu(menu, 3, 2, R.string.search, R.drawable.ic_search_actionbar);
		// ˢ�²˵�
		addMenu(menu, 4, 3, R.string.refresh, R.drawable.ic_refresh_actionbar);
		// ���ò˵�
		addMenu(menu, 5, 4, R.string.setting);
		// ���ڲ˵�
		addMenu(menu, 6, 5, R.string.about);
		// �˳�
		addMenu(menu, 7, 6, R.string.exit);
		return true;
	}

	public void addMenu(Menu menu, int itemId, int order, int titleRes) {
		addMenu(menu, itemId, order, titleRes, -1);
	}

	public void addMenu(Menu menu, int itemId, int order, int titleRes, int icon) {

		MenuItem menuItem = menu.add(0, itemId, order, titleRes)
				.setOnMenuItemClickListener(this);
		if (icon > 0) {
			menuItem.setIcon(icon);
		}

		if (itemId != 2 && itemId != 3 && itemId != 4) {
			menuItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER);
		} else {
			menuItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
		}

	}

	@Override
	public boolean onMenuItemClick(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case 11: // ʱ������
			item.setChecked(true);
			mFileInteractionHub.onMenuOperation(GlobalConsts.MENU_SORT_DATE);
			break;
		case 12: // ��������
			item.setChecked(true);
			mFileInteractionHub.onMenuOperation(GlobalConsts.MENU_SORT_NAME);
			break;
		case 13: // ��С����
			item.setChecked(true);
			mFileInteractionHub.onMenuOperation(GlobalConsts.MENU_SORT_SIZE);
			break;
		case 14: // ��������
			item.setChecked(true);
			mFileInteractionHub.onMenuOperation(GlobalConsts.MENU_SORT_TYPE);
			break;
		case 2: // �½�
			mFileInteractionHub.onMenuOperation(GlobalConsts.MENU_NEW_FOLDER);
			break;
		case 3: // ����
			Intent intent = new Intent(mContext, SearchActivity.class);
			((MainActivity) mContext).startActivity(intent);
			break;
		case 4: // ˢ��
			mFileInteractionHub.onMenuOperation(GlobalConsts.MENU_REFRESH);
			break;
		case 5: // ����
			mFileInteractionHub.onMenuOperation(GlobalConsts.MENU_SETTING);
			break;

		case 6: // ����
			mFileInteractionHub.onMenuOperation(GlobalConsts.MENU_ABOUT);
			break;

		case 7: // �˳�
			mFileInteractionHub.onMenuOperation(GlobalConsts.MENU_EXIT);
			break;

		default:
			break;
		}
		return true;
	}
}
