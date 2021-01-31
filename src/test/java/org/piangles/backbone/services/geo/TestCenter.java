/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements. See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.piangles.backbone.services.geo;

public class TestCenter
{
	public static void main(String args[])
	{
		/**
		 * Coordinates taken from OpenStreetMap
		 * https://www.openstreetmap.org/export#map=18/40.68949/-74.04477
		 */
		//TODO Simplify GeoLocation creation
		GeoLocation gl1 = new GeoLocation(new GeoCoordinate(40.69021, true), new GeoCoordinate(-74.04707, false));
		GeoLocation gl2 = new GeoLocation(new GeoCoordinate(40.68819, true), new GeoCoordinate(-74.04216, false));
		GeoLocation gl3 = center(gl1, gl2);
		System.out.println(gl3);
	}
	
	/**
	 * Center of geometry defined by coordinates
	 * Taken from 
	 * https://stackoverflow.com/questions/6671183/calculate-the-center-point-of-multiple-latitude-longitude-coordinate-pairs
	 * 
	 */
	private static double pi = Math.PI / 180;
	private static double xpi = 180 / Math.PI;

	public static GeoLocation center(GeoLocation... arr)
	{
		if (arr.length == 1)
		{
			return arr[0];
		}
		double x = 0, y = 0, z = 0;

		for (GeoLocation c : arr)
		{
			double latitude = c.getLatitude().getDecimalValue() * pi, longitude = c.getLongitude().getDecimalValue() * pi;
			double cl = Math.cos(latitude);// save it as we need it twice
			x += cl * Math.cos(longitude);
			y += cl * Math.sin(longitude);
			z += Math.sin(latitude);
		}

		int total = arr.length;

		x = x / total;
		y = y / total;
		z = z / total;

		double centralLongitude = Math.atan2(y, x);
		double centralSquareRoot = Math.sqrt(x * x + y * y);
		double centralLatitude = Math.atan2(z, centralSquareRoot);

		return new GeoLocation(new GeoCoordinate(centralLatitude * xpi, true), new GeoCoordinate(centralLongitude * xpi, false));
	}
}
