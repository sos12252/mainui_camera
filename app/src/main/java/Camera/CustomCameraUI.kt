package Camera

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.viewpager.widget.ViewPager
import com.example.Main_ui.R
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.activity_custom_camera_ui.*
//카메라 메인액티비티 camera MainActivity
class CustomCameraUI : AppCompatActivity() {

    private lateinit var camera2: Camera2
    private var disposable: Disposable? = null

    private var adapter: Adapter? = null
    private lateinit var viewPager: ViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        adapter.let{
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_custom_camera_ui)

            viewPager = findViewById<ViewPager>(R.id.viewPager)
            val adapter = Adapter(this)
            viewPager.adapter= adapter

            init()
        }
    }

    private fun init() {

        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) == PackageManager.PERMISSION_GRANTED
        )

            initCamera2Api()
        else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                requestPermissions(arrayOf(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE), 3)
            else initCamera2Api()

        }

    }

    private fun initCamera2Api() {

        camera2 = Camera2(this, camera_view)

        //카메라 회전 버튼이벤트 camera rotate event
        iv_rotate_camera.setOnClickListener {
            camera2.switchCamera()
        }
        //카메라 캡쳐 버튼이벤트 camera capture event
        iv_capture_image.setOnClickListener { v ->
            camera2.takePhoto {
                Toast.makeText(v.context, "Saving Picture", Toast.LENGTH_SHORT).show()
                disposable = Converters.convertBitmapToFile(it) { file ->
                    Toast.makeText(v.context, "Saved Picture Path ${file.path}", Toast.LENGTH_SHORT).show()
                }

            }


        }
        //카메라 플래시 이벤트 (플래시 켬) camera flash on
        iv_camera_flash_on.setOnClickListener {
            camera2.setFlash(Camera2.FLASH.ON)
            it.alpha = 1f
            iv_camera_flash_auto.alpha = 0.4f
            iv_camera_flash_off.alpha = 0.4f
        }

        //카메라 플래시 이벤트 (플래시 자동) camera flash auto
        iv_camera_flash_auto.setOnClickListener {
            iv_camera_flash_off.alpha = 0.4f
            iv_camera_flash_on.alpha = 0.4f
            it.alpha = 1f
            camera2.setFlash(Camera2.FLASH.AUTO)
        }
        //카메라 플래시 이벤트 (플래시 끔) camera flash off
        iv_camera_flash_off.setOnClickListener {
            camera2.setFlash(Camera2.FLASH.OFF)
            it.alpha = 1f
            iv_camera_flash_on.alpha = 0.4f
            iv_camera_flash_auto.alpha = 0.4f

        }

    }


    override fun onPause() {
        //  cameraPreview.pauseCamera()
        camera2.close()
        super.onPause()
    }

    override fun onResume() {
        // cameraPreview.resumeCamera()
        camera2.onResume()
        super.onResume()
    }

    override fun onDestroy() {
        if (disposable != null)
            disposable!!.dispose()
        super.onDestroy()
    }


}
