# test3
UI组件用法演示 

首先是导航页面，添加几个按钮跳转到不同的界面演示不同UI组件的用法： 

导航界面  

MainActivity1演示SimpleAdapter的用法；MainActivity2演示自定义对话框的实现；MainActivity3演示如何使用xml文件定义菜单；MainActivity4演示如何使用ACtionMode形式的上下文菜单；


MainActivity1

本界面演示了SimpleAdapter用来装配ListView的用法。ListView每个Item的布局采用相对布局，包含一个ImageView和一个TextView，并且指定ImageView对齐父类布局的右侧。

注意：ListView条目单击显示颜色可以指定其listSelector属性。  

<?xml version="1.0" encoding="utf-8"?>
<LinearLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:orientation="horizontal">
    <ListView
        android:id="@+id/MyList"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:listSelector="@color/colorAccent">

    </ListView>

</LinearLayout>
自定义对话框的实现
MainActivity2

自定义对话框使用getLayoutInflater()获取LayoutInflater实例，并利用LayoutInflater的inflate()方法从自定义布局文件中加载对话框的布局，从而实现自定义对话框。对话框的布局如下：

<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
android:layout_width="match_parent"
android:layout_height="wrap_content"
android:orientation="vertical">

    <TextView
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="@string/android_app"
        android:textSize="40dp"
        android:textColor="#F1EFF3"
        android:background="@android:color/holo_orange_light"
        android:gravity="center_horizontal" />

    <EditText
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:hint="@string/username"
        android:id="@+id/btn_u" />

    <EditText
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:hint="@string/password"
        android:id="@+id/btn_p"
        />
    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        >

        <Button
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_weight="1"
            android:background="@drawable/shape"
            android:id="@+id/btn_c"
            android:text="@string/cancel"
            />

        <Button
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_weight="1"
            android:background="@drawable/shape"
            android:id="@+id/btn_s"
            android:text="@string/sign_in" />

    </LinearLayout>

</LinearLayout>

使用XML定义菜单
XmlDefineMenu

在res文件夹下新建menu文件夹，并新建一个xml文件来定义菜单，具体的XML文件内容:

<?xml version="1.0" encoding="utf-8"?>
<menu xmlns:android="http://schemas.android.com/apk/res/android">

    <item
        android:id="@+id/submenu1"
        android:icon="@mipmap/ic_launcher"
        android:title="@string/submenu_title1">
        <menu>
            <item
                android:id="@+id/submenu_item1"
                android:title="@string/submenu_item1"/>
            <item
                android:id="@+id/submenu_item2"
                android:title="@string/submenu_item2"/>
            <item
                android:id="@+id/submenu_item3"
                android:title="@string/submenu_item3"/>
        </menu>
        </item>
    <item
        android:id="@+id/item1"
        android:icon="@mipmap/ic_launcher"
        android:title="@string/item1"/>

    <item
        android:id="@+id/submenu2"
        android:title="@string/submenu_title2">
        <menu>
            <item
                android:id="@+id/submenu_item4"
                android:title="@string/submenu_item4"/>
            <item
                android:id="@+id/submenu_item5"
                android:title="@string/submenu_item5"/>
        </menu>
    </item>

</menu>
创建ActionMode模式的上下文菜单
ActionModeContextMenu

上下文操作模式是Android3.0以后添加新特性，是上下文菜单的首选模式。

应用如何调用上下文操作模式以及如何定义每个操作的行为，具体取决于您的设计。 设计基本上分为两种：

针对单个任意视图的上下文操作。
针对 ListView 或 GridView 中项目组的批处理上下文操作（允许用户选择多个项目并针对所有项目执行操作）。
这里演示在 ListView 或 GridView 中启用批处理上下文操作

如果在 ListView 或 GridView 中有一组项目（或 AbsListView 的其他扩展），且需要允许用户执行批处理操作，则应：

实现 AbsListView.MultiChoiceModeListener 接口，并使用 setMultiChoiceModeListener() 为视图组设置该接口。在侦听器的回调方法中，您既可以为上下文操作栏指定操作，也可以响应操作项目的点击事件，还可以处理从 ActionMode.Callback 接口继承的其他回调。
使用 CHOICE_MODE_MULTIPLE_MODAL 参数调用 setChoiceMode()。
一个简单的示例：

public class MainActivity4 extends AppCompatActivity {
    private ListView listView;
    private List<Item> list;
    private MyAdapter adapter;
    private String[] names = new String[]{"One","Two","Three","Four","Five"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        listView = findViewById(R.id.mylist2);
        list = new ArrayList<>();
        for(int i=0;i<names.length;i++){
            list.add(new Item(names[i],false));
        }
        adapter = new MyAdapter(list,MainActivity4.this);
        listView.setAdapter(adapter);

        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        listView.setMultiChoiceModeListener(new MultiChoiceModeListener() {
            int num = 0;
            public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked) {

               /**实时刷新标志位，并计算选择的项目数 */
                if (checked) {
                    list.get(position).setBo(true);
                    adapter.notifyDataSetChanged();
                    num++;
                } else {
                    list.get(position).setBo(false);
                    adapter.notifyDataSetChanged();
                    num--;
                }
                mode.setTitle("  " + num + " Selected");
            }


            /*
             * 参数：ActionMode是长按后出现的标题栏
             * 		Menu是标题栏的菜单内容
             */
            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                // 设置长按后所要显示的标题栏的内容
                MenuInflater inflater = mode.getMenuInflater();
                inflater.inflate(R.menu.menu2, menu);
                num = 0;
                adapter.notifyDataSetChanged();
                return true;

            }


            /*
             * 可在此方法中进行标题栏UI的创建和更新
             */
            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {

                adapter.notifyDataSetChanged();
                return false;
            }

            public void refresh(){
                for(int i = 0; i < 6; i++){
                    list.get(i).setBo(false);
                }
            }
            public void delete(){
                for(int i=0;i<list.size();i++){
                    if(list.get(i).isBo()){
                        list.remove(i);
                    }
                }
            }
            /*
             * 可在此方法中监听标题栏Menu的监听，从而进行相应操作
             * 设置actionMode菜单每个按钮的点击事件
             */
            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                switch (item.getItemId()) {
                    //全选
                    case R.id.action_setting:
                        num = 0;
                        refresh();
                        adapter.notifyDataSetChanged();
                        mode.finish(); // 偷了个懒，每个菜单按钮都设置返回，结束多选模式
                        return true;
                    //删除
                    case R.id.action_delete:
                        adapter.notifyDataSetChanged();
                        num = 0;
                        delete();
                        mode.finish();// 偷了个懒，每个菜单按钮都设置返回，结束多选模式
                        return true;
                    default:
                        refresh();
                        adapter.notifyDataSetChanged();
                        num = 0;
                        return false;
                }

            }

            @Override
            public void onDestroyActionMode(ActionMode mode) {
                refresh();
                adapter.notifyDataS
            etChanged();

            }

        });
    }
}
