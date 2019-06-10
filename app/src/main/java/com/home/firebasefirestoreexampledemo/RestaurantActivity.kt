package com.home.firebasefirestoreexampledemo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_restaurant.*

class RestaurantActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restaurant)
        createFirestoreData()
    }

    /**
     * 創建Firestore的資料
     */
    private fun createFirestoreData() {
        val collectionPath = "restaurantList"
        val firestore = FirebaseFirestore.getInstance()
        for (restaurant in getRestaurantList()) {
            firestore.collection(collectionPath).document(restaurant.id).set(restaurant)
        }
        observeFirestoreData(firestore, collectionPath)
    }

    /**
     * 監聽Firestore的資料, 如果有任何變動 會即時通知並顯示
     */
    private fun observeFirestoreData(
        firestore: FirebaseFirestore,
        collectionPath: String
    ) {
        firestore.collection(collectionPath)
            .addSnapshotListener { querySnapshot, exception ->
                val restaurantList = ArrayList<RestaurantData>()
                if (exception == null) {
                    for (restaurant in querySnapshot!!.documents) { // 取得該集合的所有文件
                        restaurantList.add(restaurant.toObject(RestaurantData::class.java)!!) // 根據該model的需求欄位, 將文件轉換成該model
                    }
                }
                val adapter = RestaurantRecyclerAdapter(restaurantList)
                recyclerView.adapter = adapter
            }
    }

    private fun getRestaurantList(): List<RestaurantData> {
        return listOf(
            RestaurantData(
                "001",
                "A CUT牛排館",
                "國賓飯店的 A CUT，從過去到現在一直是牛排界的頂級選項, 當然, 價位也非常頂級。雖然是牛排教父鄧有癸待過的餐廳, 但近年也有走出自己特色，已經有獨自的風格了，可以說是很頂級的牛排館。",
                "12%折抵",
                "1500",
                "https://pic.pimg.tw/change84/1534757824-3879581734_l.jpg",
                true
            ),
            RestaurantData(
                "002",
                "雅室牛排",
                "曾幾何時, 我們已經需要用『老店』來形容這間店了。一直保持著牛排的美味, 而且菜色也有進步, 已是很多人對『高檔牛排的回憶』的這間店, 仍然是聚餐相當不錯的選項。",
                "5%折抵",
                "700",
                "http://www.steakinn.com.tw/web/upload/201311281546472sRaL-9.jpg",
                true
            ),
            RestaurantData(
                "003",
                "歐華地中海牛排",
                "如果要說好吃牛排,很多人都會提到這一間, 但它也可以說是最低調的高檔牛排。\n" +
                        "位置很低調,名氣也還好，但味道則是無庸置疑。",
                "30%折抵",
                "2500",
                "https://img.joycelohas.com/20170309213702_95.jpg",
                true
            ),
            RestaurantData(
                "004",
                "教父牛排",
                "牛排教父鄧有癸的店, 台北2018米其林一星認證。\n" +
                        "台北唯一一間得到米其林一星的牛排館, 光這些就可以覺得它很厲害了吧XD",
                "21%折抵",
                "3500",
                "https://cdn.walkerland.com.tw/images/upload/poi/p48976/m26120/431a8113f9cd20cab0493c222f4d4346bce5eca6.jpg",
                true
            ),
            RestaurantData(
                "005",
                "亞理士西餐廳",
                "超老牌台式西餐牛排館之一, 有著現炒的巧克力飲品, 頂級的真空管音響, 可以說是台北吃牛排早年到現在一直的經典。\n" +
                        "當然, 老式牛排館的特色就是, 牛排很好, 但其他配菜就遜色了些。但對於大多數人來說, 已經是個不錯的選擇了,而且環境也很舒服。",
                "22%折抵",
                "1900",
                "https://pic.pimg.tw/lafattehome/1380076244-2121360830.jpg",
                false
            ),
            RestaurantData(
                "006",
                "茹絲葵",
                "許多人對於高檔牛排心中的經典, 美國Prime 牛肉的代表\n" +
                        "加上許多美式好吃配菜, 這就是牛排^^\n" +
                        "至於什麼牛排界的LV , 還是覺得是不倫不類的比較",
                "32%折抵",
                "5500",
                "https://3.bp.blogspot.com/-QgnR__lpgf8/VhTfF_zViGI/AAAAAAAAmMs/2vgdroGScd4/s1600/IMG_2398-2.jpg",
                true
            )
        )
    }
}
