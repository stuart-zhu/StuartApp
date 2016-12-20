package com.stuart.stuartapp.demo.activity;

import android.Manifest;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.CommonDataKinds.StructuredName;
import android.provider.ContactsContract.RawContacts;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.stuart.stuartapp.BaseActivity;
import com.stuart.stuartapp.R;
import com.stuart.stuartapp.utils.ToastUtil;

import java.io.UnsupportedEncodingException;
import java.util.Random;

import static android.provider.ContactsContract.Contacts.Data.*;

/**
 * Created by stuart on 2016/12/12.
 */
public class AddContacts extends BaseActivity implements View.OnClickListener{


    private static final int MESSAGE_ADD_CONTACT = 1;
    private static final int MESSAGE_ADD_COMPLETE = 2;
    private Button btSubmit;
    private EditText etCount;

    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MESSAGE_ADD_CONTACT:
                    final int i = msg.arg1;
                    new Thread() {
                        public void run() {
                            for (int a = 0; a < i; a++) {
                                int x = new Random().nextInt(3) + 1;
                                StringBuffer sb = new StringBuffer();
                                for (int q = 0; q < x; q++) {
                                    sb.append(new RandomHan().getRandomHan());
                                }
                                AddContact(sb.toString(),
                                        getRandomPhone());

                                Message.obtain(handler, MESSAGE_ADD_COMPLETE, a + 1, i)
                                        .sendToTarget();
                            }
                        }

                        ;
                    }.start();
                    break;

                case MESSAGE_ADD_COMPLETE:
                    int x = msg.arg1;
                    tos("成功" + x + "个");
                    break;
            }
        }

        ;
    };


    private void setView() {
        btSubmit = (Button) findViewById(R.id.addcontact_bt);
        etCount = (EditText) findViewById(R.id.addcontact_et);
        btSubmit.setOnClickListener(this);
    }

    private  static final int MY_PERMISSIONS_REQUEST = 111;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.demo_act_addcontact);

        if (Build.VERSION.SDK_INT >= 23)
        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {

//            ActivityCompat.shouldShowRequestPermissionRationale()
            ActivityCompat.requestPermissions(this,
                    new String[] {Manifest.permission.READ_CONTACTS, Manifest.permission.WRITE_CONTACTS
                    },
                    MY_PERMISSIONS_REQUEST
            );
        }


        setView();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST:
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    ToastUtil.getInstance(this).show("不给权限就难办了！！！");
                    finish();
                }
                return;

        }
    }

    // 往数据库中新增联系人
    public void AddContact(String name, String number) {
        ContentValues values = new ContentValues();
        // 首先向RawContacts.CONTENT_URI执行一个空值插入，目的是获取系统返回的rawContactId
        Uri rawContactUri = getContentResolver().insert(
                RawContacts.CONTENT_URI, values);
        long rawContactId = ContentUris.parseId(rawContactUri);
        // 往data表插入姓名数据
        values.clear();
        values.put(RAW_CONTACT_ID, rawContactId);
        values.put(MIMETYPE, StructuredName.CONTENT_ITEM_TYPE);// 内容类型
        values.put(StructuredName.GIVEN_NAME, name);
        getContentResolver().insert(ContactsContract.Data.CONTENT_URI, values);
        // 往data表插入电话数据
        values.clear();
        values.put(RAW_CONTACT_ID, rawContactId);
        values.put(MIMETYPE, Phone.CONTENT_ITEM_TYPE);
        values.put(Phone.NUMBER, number);
        values.put(Phone.TYPE, Phone.TYPE_MOBILE);
        getContentResolver().insert(ContactsContract.Data.CONTENT_URI, values);
    }

    // 更改数据库中联系人
    public void ChangeContact(String name, String number, String ContactId) {
        Log.i("huahua", name);
        ContentValues values = new ContentValues();
        // 更新姓名
        values.put(StructuredName.GIVEN_NAME, name);
        getContentResolver().update(ContactsContract.Data.CONTENT_URI, values,
                RAW_CONTACT_ID + "=? and " + MIMETYPE + "=?",
                new String[]{ContactId, StructuredName.CONTENT_ITEM_TYPE});
        // 更新电话
        values.clear();
        values.put(Phone.NUMBER, number);
        getContentResolver().update(ContactsContract.Data.CONTENT_URI, values,
                RAW_CONTACT_ID + "=? and " + MIMETYPE + "=?",
                new String[]{ContactId, Phone.CONTENT_ITEM_TYPE});
    }

    // 删除联系人
    public void DeleteContact(String ContactId) {
        Uri uri = Uri.withAppendedPath(RawContacts.CONTENT_URI, ContactId);
        getContentResolver().delete(uri, null, null);
        // 此删除方法不全面
        // getContentResolver().delete(ContactsContract.Data.CONTENT_URI,
        // Data.RAW_CONTACT_ID + "=? ",
        // new String[] { ContactId});
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
    }

    // 往短信数据库中新增发送的短信
    public void AddSms(String Number, String Content, Long Date, int Type) {
        ContentValues values = new ContentValues();
        values.put("address", Number);
        values.put("body", Content);
        values.put("date", Date);
        values.put("type", Type);
        Uri result = getContentResolver().insert(Uri.parse("content://sms/"),
                values);
    }


    /*
     * public class Test { public static void main(String[] args) { RandomHan
     * han = new RandomHan(); System.out.print(han.getRandomHan()); }
     *
     * }
     */
    class RandomHan {
        private Random ran = new Random();
        private final static int delta = 0x9fa5 - 0x4e00 + 1;

        public String getRandomHan() {
           /* return (char) (0x4e00 + ran.nextInt(delta));*/

            String str = null;
            int hightPos, lowPos; // 定义高低位
            Random random = new Random();
            hightPos = (176 + Math.abs(random.nextInt(39)));//获取高位值
            lowPos = (161 + Math.abs(random.nextInt(93)));//获取低位值
            byte[] b = new byte[2];
            b[0] = (new Integer(hightPos).byteValue());
            b[1] = (new Integer(lowPos).byteValue());
            try {
                str = new String(b, "GBk");//转成中文
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            return str;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.addcontact_bt:
                String et = null;
                if (etCount.getText() != null) {
                    et = etCount.getText().toString();
                }
                if (et == null) {
                    tos("你这不行");
                }
                try {
                    int i = Integer.parseInt(et);
                    Message.obtain(handler, MESSAGE_ADD_CONTACT, i, i)
                            .sendToTarget();
                } catch (NumberFormatException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    tos("你这不行");
                }

                break;
        }

    }

    private void tos(String s) {
        ToastUtil.getInstance(this).show(s);
    }


    /**
     * 验证手机号码（支持国际格式，+86135xxxx...（中国内地），+00852137xxxx...（中国香港））
     * @param mobile 移动、联通、电信运营商的号码段
     *<p>移动的号段：134(0-8)、135、136、137、138、139、147（预计用于TD上网卡）
     *、150、151、152、157（TD专用）、158、159、187（未启用）、188（TD专用）</p>
     *<p>联通的号段：130、131、132、155、156（世界风专用）、185（未启用）、186（3g）</p>
     *<p>电信的号段：133、153、180（未启用）、189</p>
     */

    private static final String [] PHONE_NUMBER_HEADER = {
      // 移动
            "134","135","136","137","138","139","147","150","151","152","157","158","159","187","188",
            // 联通
            "130","131","132","155","156","185","186",
            // 电信
            "133", "153", "180", "189"
    };

    /**
     * 获取随机的电话号码
     * @return
     */
    private static String getRandomPhone() {
        int x = new Random().nextInt(PHONE_NUMBER_HEADER.length);
        String phoneNumber = PHONE_NUMBER_HEADER[x];
        while (phoneNumber.length() < 11) {
            phoneNumber += getRandomNumber();
        }
        return phoneNumber;
    }

    /**
     * 获取随机小于10 的数
     * @return
     */
    private static int getRandomNumber() {
        return new Random().nextInt(10);
    }
}
