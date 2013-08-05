/**
 * 
 */
package com.liveclips.soccer.adapter;

import java.util.LinkedHashMap;
import java.util.Map;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;

/**
 * @author mohitkumar
 *
 */
public class EmptyHeaderSeparatedListAdapter extends BaseAdapter {

	public Map<String, Adapter> sections = new LinkedHashMap<String, Adapter>();
	//public ArrayAdapter<String> headers;
	//public final static int TYPE_SECTION_HEADER = 0;

	
	
	public EmptyHeaderSeparatedListAdapter(Context context, boolean headerBlank) {
		super();
        sections.clear();
	}

	public void addSection(String section, Adapter adapter) {
			this.sections.put(section, adapter);
	}
	
	public void removeAllSections() {
		this.sections.clear();
	}

	public Object getItem(int position) {
		for (Object section : this.sections.keySet()) {
			Adapter adapter = sections.get(section);
			int size = adapter.getCount();

			// check if position inside this section
			//if (position == 0)
				//return section;
			if (position < size)
				return adapter.getItem(position);

			// otherwise jump into next section
			position -= size;
		}
		return null;
	}

	public int getCount() {
		// total together all sections, plus one for each section header
		int total = 0;
		for (Adapter adapter : this.sections.values())
			total += adapter.getCount();
		return total;
	}

	public int getViewTypeCount() {
		// assume that headers count as one, then total all sections
		int total = 0;
		for (Adapter adapter : this.sections.values())
			total += adapter.getViewTypeCount();
		return total;
	}

	public int getItemViewType(int position) {
		int type = 0;
		for (Object section : this.sections.keySet()) {
			Adapter adapter = sections.get(section);
			int size = adapter.getCount() ;

			// check if position inside this section
			//if (position == 0)
				//return TYPE_SECTION_HEADER;
			if (position < size)
				return type + adapter.getItemViewType(position);

			// otherwise jump into next section
			position -= size;
			type += adapter.getViewTypeCount();
		}
		return -1;
	}

	public boolean areAllItemsSelectable() {
		return false;
	}

	public boolean isEnabled(int position) {
		return true;//(getItemViewType(position) != TYPE_SECTION_HEADER);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		int sectionnum = 0;
		for (Object section : this.sections.keySet()) {
			Adapter adapter = sections.get(section);
			int size = adapter.getCount();

			// check if position inside this section
			//if (position == 0)
			//	return //headers.getView(sectionnum, convertView, parent);
			if (position < size)
				return adapter.getView(position, convertView, parent);

			// otherwise jump into next section
			position -= size;
			sectionnum++;
		}
		return null;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

}
