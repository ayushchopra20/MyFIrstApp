Sensor Data Display in Android App

Overview

This project focuses on displaying sensor data in an Android application. Initially, the app displayed sensor information in a plain text format. The goal was to improve the UI by arranging the sensor names on the left side and their corresponding data on the right side in a structured row-column format.

Problem Statement:

The initial design presented sensor data in a linear format, making it difficult to read and compare different sensor attributes. The objective was to:

Organize sensor names and data into rows and columns.

Improve UI readability by using a structured layout.This is an android app that shows current date and time.

Solution Implementation:

1. Using TableLayout for Organizing Data

A TableLayout was implemented to arrange sensor names in the left column and corresponding data in the right column. Each sensor's information is displayed in a TableRow to maintain a structured layout.

2. Updating Sensor Data Programmatically

To dynamically update sensor data, the following logic is implemented in MainActivity.java (or MainActivity.kt for Kotlin):

Features Implemented:

✅ Organized sensor data in a table layout.
✅ Dynamically updated sensor data using Java/Kotlin.
✅ Improved UI readability by structuring the data properly.

Future Enhancements:

Add more sensors dynamically.

Implement real-time updates from sensor listeners.

Use RecyclerView for better scalability with multiple sensors.

Conclusion:

This project successfully improved the display of sensor data in an Android application by using a structured TableLayout. The UI is now more readable and easier to understand for users who need sensor-related information.
