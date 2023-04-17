package com.svoboda.ebike.domain.usecase

import android.content.Context
import android.util.Xml
import com.svoboda.ebike.R
import com.svoboda.ebike.domain.model.WheelSize
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserException
import java.io.IOException

/**
 * Parses the wheel sizes XML file and returns a list of WheelSize objects.
 */
class ParseWheelSizesUseCase(
    private val context: Context
) {
    operator fun invoke(): List<WheelSize> {
        val wheelSizes = mutableListOf<WheelSize>()

        try {
            val parser = Xml.newPullParser()
            val inputStream = context.resources.openRawResource(R.raw.wheel_sizes)
            parser.setInput(inputStream, null)
            var eventType = parser.eventType

            while (eventType != XmlPullParser.END_DOCUMENT) {
                if (eventType == XmlPullParser.START_TAG && parser.name == "wheelSize") {
                    var inches: Double? = null
                    var meters: Double? = null

                    while (eventType != XmlPullParser.END_TAG || parser.name != "wheelSize") {
                        eventType = parser.next()
                        if (eventType == XmlPullParser.START_TAG) {
                            when (parser.name) {
                                "inches" -> {
                                    eventType = parser.next()
                                    inches = parser.text?.toDouble()
                                }

                                "meters" -> {
                                    eventType = parser.next()
                                    meters = parser.text?.toDouble()
                                }
                            }
                        }
                    }

                    if (inches != null && meters != null) {
                        wheelSizes.add(WheelSize(inches, meters))
                    }
                }
                eventType = parser.next()
            }

            inputStream.close()
        } catch (e: XmlPullParserException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return wheelSizes
    }
}
