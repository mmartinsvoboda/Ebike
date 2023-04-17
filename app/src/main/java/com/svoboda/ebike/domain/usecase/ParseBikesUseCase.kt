package com.svoboda.ebike.domain.usecase

import android.content.Context
import com.svoboda.ebike.R
import com.svoboda.ebike.domain.model.Bike
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserFactory

/**
 * Parses the bikes XML file and returns a list of Bike objects.
 */
class ParseBikesUseCase(
    private val context: Context
) {
    operator fun invoke(): List<Bike> {
        val bikes = mutableListOf<Bike>()
        val parserFactory = XmlPullParserFactory.newInstance()
        val parser = parserFactory.newPullParser()

        val inputStream = context.resources.openRawResource(R.raw.bikes)
        parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false)
        parser.setInput(inputStream, null)

        var eventType = parser.eventType
        while (eventType != XmlPullParser.END_DOCUMENT) {
            if (eventType == XmlPullParser.START_TAG && parser.name == "bike") {
                val bike = parseBike(parser)
                bikes.add(bike)
            }
            eventType = parser.next()
        }
        inputStream.close()

        return bikes
    }

    private fun parseBike(parser: XmlPullParser): Bike {
        val name = parser.getAttributeValue(null, "name")
        val maxRpm = parser.getAttributeValue(null, "maxRpm").toInt()
        val gearRatio = parser.getAttributeValue(null, "gearRatio").toDouble()

        return Bike(name = name, maxRpm = maxRpm, gearRatio = gearRatio)
    }
}
