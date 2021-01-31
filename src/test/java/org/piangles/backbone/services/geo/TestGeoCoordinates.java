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

import java.text.ParseException;

public class TestGeoCoordinates
{

	public static void main(String[] args) throws ParseException
	{
		// TODO Auto-generated method stub
		double value = 38.889722;
		GeoCoordinate gc1, gc2, gc3, gc4;
		
		System.out.println("Value:" + value);
		gc1 = new GeoCoordinate(value, true);
		System.out.println("GC1 DecimalValue:" + gc1.getDecimalValue());
		System.out.println(gc1);
		System.out.println(gc1.getDMSRepresentation());

		gc2 = new GeoCoordinate(38, 53, 23, Direction.North);
		System.out.println("GC2 DecimalValue:" + gc2.getDecimalValue());
		System.out.println(gc2);
		System.out.println(gc2.getDMSRepresentation());

		gc3 = GeoCoordinate.parse(gc1.getDMSRepresentation());
		System.out.println("GC3 DecimalValue:" + gc3.getDecimalValue());
		System.out.println(gc3);
		System.out.println(gc3.getDMSRepresentation());

		System.out.println("Exact gc1 Vs gc2 = " + gc1.equals(gc2));
		System.out.println("Exact gc1 Vs gc3 = " + gc1.equals(gc3));
		
		System.out.println("Approximate gc1 Vs gc2 = " + gc1.equalsApproximate(gc2));
		System.out.println("Approximate gc1 Vs gc3 = " + gc1.equalsApproximate(gc3));
		
		gc4 = new GeoCoordinate(value, true);
		System.out.println("Exact gc1 Vs gc4 = " + gc1.equals(gc4));
		
		String coords = "78°14'09\" N";
		System.out.println("ToParse:" + coords);
		GeoCoordinate gc = GeoCoordinate.parse(coords);
		System.out.println(gc);
	}
}
