package work.curioustools.sizeutils

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.provider.OpenableColumns.DISPLAY_NAME
import android.provider.OpenableColumns.SIZE
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import work.curioustools.sizeunit.SizeUnit
import work.curioustools.sizeunit.Tester
import work.curioustools.sizeunit.helpers.roundOff
import work.curioustools.sizeunit.helpers.toMemoryString
import work.curioustools.sizeutils.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding by  lazy { ActivityMainBinding.inflate(layoutInflater) }

    private val launcher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            try {
                val data = it.data?.data
                if (data == null) {
                    Log.e(TAG, "data is null")
                    return@registerForActivityResult
                }
                contentResolver.query(data, null, null, null, null)?.use { cursor ->
                    if (cursor.moveToFirst()) {
                        val nameColumn = cursor.getColumnIndex(DISPLAY_NAME)
                        val sizeColumn = cursor.getColumnIndex(SIZE)
                        val fileName:String? = cursor.getString(nameColumn)
                        val fileSize = cursor.getLong(sizeColumn)
                        val fileExtension = getFileExtension(fileName?:"")
                        val filePath = data.path.orEmpty()

                        val icon = when (fileExtension) {
                            in arrayOf("png", "jpeg", "jpg") -> R.drawable.ic_image
                            in arrayOf("mkv", "mp4", "mov") -> R.drawable.ic_video
                            in arrayOf("mp3") -> R.drawable.ic_audio
                            else -> R.drawable.ic_file
                        }
                        updateUI(fileName, filePath, icon, fileSize)

                    }
                }

            }catch (t:Throwable){
                t.printStackTrace()
            }
        }

    @SuppressLint("SetTextI18n")
    private fun updateUI(name: String?=null, path: String="", icon:Int = -1, size:Long = -1) {
        runOnUiThread {
            with(binding){
                if(name!=null){
                    ivFileIcon.isVisible = true
                    ivFileIcon.setImageResource(icon)

                    tvFileInfo.isVisible =true
                    tvFileInfo.text = "name=$name\npath=$path"

                    btAddOrReplace.text = "Update File"

                    tvSize.isVisible = true
                    tvSize.text = "${size.toMemoryString()} | ${SizeUnit.Bytes(size).toMegaBytes().roundOff(3)}MB"

                }
                else{
                    ivFileIcon.isVisible = false
                    tvFileInfo.isVisible = false
                    tvSize.isVisible = false
                    btAddOrReplace.text = "Open a  File"
                }
            }
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        with(binding){
            btAddOrReplace.setOnClickListener {
                launcher.launch(
                    Intent(Intent.ACTION_OPEN_DOCUMENT).also {
                        it.addCategory(Intent.CATEGORY_OPENABLE)
                        it.type = "*/*"
                    }
                )
            }
            btShowSample.setOnClickListener {
                Tester.testString().let { tvSample.text = it }
                btShowSample.isVisible = false
            }
        }


        updateUI()


    }
    private fun getFileExtension(fileName: String): String {
        val dotIndex = fileName.lastIndexOf('.')
        return if (dotIndex == -1) "" else fileName.substring(dotIndex + 1)
    }

    companion object{
        const val TAG = "MainActivity>>"
    }





}