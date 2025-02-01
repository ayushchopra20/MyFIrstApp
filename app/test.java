activity_main.xml

<?xml version="1.0" encoding="utf-8"?>
<LinearLayout
xmlns:android="http://schemas.android.com/apk/res/android"
xmlns:tools="http://schemas.android.com/tools"
xmlns:app="http://schemas.android.com/apk/res-auto"
android:id="@+id/main"
android:layout_width="match_parent"
android:layout_height="match_parent"
tools:context=".MainActivity"
android:orientation="vertical"
android:layout_marginLeft="25px"
        >

    <TextView
android:id="@+id/tv_date"
android:layout_width="wrap_content"
android:layout_height="wrap_content"
android:text="@string/date_today"
android:textSize="30dp"
app:layout_constraintBottom_toBottomOf="parent"
app:layout_constraintEnd_toEndOf="parent"
app:layout_constraintStart_toStartOf="parent"
app:layout_constraintTop_toTopOf="parent" />

    <TextView
android:id="@+id/tv_date_now"
android:layout_width="wrap_content"
android:layout_height="wrap_content"
android:textSize="20dp"

        />

    <LinearLayout
android:id="@+id/ll_btn_array"
android:layout_width="wrap_content"
android:layout_height="wrap_content"
android:orientation="horizontal">

        <Button
android:id="@+id/btn_lft_btn"
android:layout_width="wrap_content"
android:layout_height="wrap_content"
android:layout_marginRight="15px"
android:text="Left Button" />

        <Button
android:id="@+id/btn_rgt_btn"
android:layout_width="wrap_content"
android:layout_height="wrap_content"
android:text="Right Button" />

    </LinearLayout>

</LinearLayout>