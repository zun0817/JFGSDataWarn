package com.ztzb.data.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;

import androidx.core.content.ContextCompat;

import com.ztzb.data.R;
import com.ztzb.data.model.data.Children;
import com.ztzb.data.model.data.ChildrenX;
import com.ztzb.data.model.data.SectionBean;
import com.ztzb.data.model.data.SectionItem;
import com.ztzb.data.util.ViewHolder;

import java.util.List;

public class ExpandableAdapter extends BaseExpandableListAdapter {

    /** 数据集合 */
    private List<SectionBean> mExpandableModeList;
    /** 上下文 */
    private Context mContext;
    /** ViewHolder */
    private ViewHolder mViewHolder;

    public ExpandableAdapter(List<SectionBean> expandableModeList, Context context) {
        mExpandableModeList = expandableModeList;
        mContext = context;
        mViewHolder = ViewHolder.getInstance();
    }

    /**
     * @return 返回组item数量
     */
    @Override
    public int getGroupCount() {
        return mExpandableModeList == null ? 0 : mExpandableModeList.size();
    }

    /**
     * 根据出入的组groupPosition返回子item的数量。
     *
     * @param groupPosition groupPosition
     * @return 返回子item的数量。
     */
    @Override
    public int getChildrenCount(int groupPosition) {
        if (mExpandableModeList == null) return 0;
        List<SectionItem> childDataBeans = mExpandableModeList.get(groupPosition).getData();
        return childDataBeans.size();
    }

    /**
     * 获取组实体对象
     *
     * @param groupPosition groupPosition
     * @return 返回组实体对象
     */
    @Override
    public Object getGroup(int groupPosition) {
        return mExpandableModeList == null ? null : mExpandableModeList.get(groupPosition);
    }

    /**
     * 获取子实体对象
     *
     * @param groupPosition groupPosition
     * @param childPosition childPosition
     * @return 返回子实体对象
     */
    @Override
    public Object getChild(int groupPosition, int childPosition) {
        if (mExpandableModeList == null) return null;
        List<SectionItem> childDataBeans = mExpandableModeList.get(groupPosition).getData();
        return childDataBeans.get(childPosition);
    }

    /**
     * 获取组id,我理解为获取组的唯一ID，一般自己返回groupPosition即可
     * @param groupPosition groupPosition
     * @return Position
     */
    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    /**
     * 获取子ID
     * @param groupPosition groupPosition
     * @param childPosition childPosition
     * @return 返回子id
     */
    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }


    /**
     * 用来判断ExpandableListView内容id是否有效的,我也不太明白，它具体有什么作用，设置返回true和false的效果是一样的。
     *
     * @return true or false
     */
    @Override
    public boolean hasStableIds() {
        return false;
    }

    /**
     * 获取给定分组的视图，主要重写的方法。
     *
     * @param groupPosition groupPosition
     * @param isExpanded    该组是展开状态还是收起状态
     * @param convertView   convertView
     * @param parent        parent
     * @return View
     */
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.layout_item_group, parent, false);
        }
        // 获取组的实体对象
        SectionBean expandableMode = mExpandableModeList.get(groupPosition);
        // 获取组名
        String groupName = expandableMode.getName();
        // 设置TextView的文字
        mViewHolder.setText(convertView, R.id.item_group_name_tv, groupName);

        // 设置组item展开或者关闭的右图片
        Drawable drawableRight = ContextCompat.getDrawable(mContext, R.mipmap.icon_expand_down);
        if (isExpanded) {
            // 展开
            drawableRight = ContextCompat.getDrawable(mContext, R.mipmap.icon_expand_up);
        }
        mViewHolder.setImage(convertView, R.id.item_group_name_img, drawableRight);

        return convertView;
    }

    /**
     * 获取给定分组给定子位置的数据用的视图，主要重写的方法。
     *
     * @param groupPosition groupPosition
     * @param childPosition childPosition
     * @param isLastChild   是否为改组最后一个子视图
     * @param convertView   convertView
     * @param parent        parent
     * @return View
     */
    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.layout_item_child, parent, false);
        }
        SectionBean expandableMode = mExpandableModeList.get(groupPosition);
        List<SectionItem> childDataBeans = expandableMode.getData();
        SectionItem childDataBean = childDataBeans.get(childPosition);
        // 获取子名字
        String childName = childDataBean.getExcavating_tunnel();

        String dun_name = childDataBean.getDun_name();
        // 设置文字
        mViewHolder.setText(convertView, R.id.item_child_name_tv, childName);
        mViewHolder.setText(convertView, R.id.item_child_no_tv, dun_name);
        return convertView;
    }

    /**
     * 子item是否可点击
     * 当isChildSelectable方法返回false的时候，子item不可点击，且不会绘制分割线
     *
     * @param groupPosition groupPosition
     * @param childPosition childPosition
     * @return true可点击 false不可点击
     */
    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }


    /**
     * 通过文件名，获取对应的图片资源
     *
     * @param context  context
     * @param filename filename
     * @return 对应的图片资源
     */
    public int getImageMipmapRes(Context context, String filename) {
        return context.getResources().getIdentifier(filename, "mipmap", context.getPackageName());
    }

    public void setExpandableModeList(List<SectionBean> expandableModeList) {
        mExpandableModeList = expandableModeList;
    }
}
