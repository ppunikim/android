package com.callor.helloapp

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.callor.helloapp.databinding.ActivityMainBinding

/*
 안드로이드 어플들은 대부분 class를 상속받아 사용한다.
 AppCompatActivity 화면을 구현하는 중요한 클래스이다.
 */

class MainActivity : AppCompatActivity() {

    /* lateinit
    코틀린은, 변수를 선언과 동시에 초기화 하는 것이 원칙이다.
    하지만 이 키워드를 부착하면 초기화를 뒤로 미룰 수 있다.
    */
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    /* onCreate
    안드로이드 어플이 시작될 때 자동으로 호출되는 method이다.
    이것은 화면이 생성될 때 호출되는 event핸들러이다.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)

        /* ActivityMainBinding
        Activity_main.xml을 확장하여 객체변수처럼 사용할 수 있도록 만드는 과정이다.
         */
        binding = ActivityMainBinding.inflate(layoutInflater)

        // 이곳부터 확장한 xml파일을 화면에 표현하기 시작한다.
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

        binding.fab.setOnClickListener { view ->
            /* snackbar
             js의 alert 과 같은 역할을 한다.
             이런 패턴은 builder패턴이라고 한다.
             */
            /* class chaining
            객체를 dot 으로 연결해 연속적인 기능 수행하기.
            */

            Snackbar.make(view, "안녕?", Snackbar.LENGTH_LONG).show()  //키보드가 필요하지 않은 화면에서 사용,
                                                                           // 맞춤형 제작 사용 가능
            //Toast.makeText(this, "안녕", Toast.LENGTH_SHORT).show()     //키보드가 필요한 화면에서 사용
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    /*
    content_main.xml에 선언된 fragment를 NavController로 등록
    화면 상단의 appBar에 fragment 메세지가 나타나도록 설정하기.
     */
    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}