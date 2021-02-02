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

public class GeoLocationServiceImpl implements GeoLocationService
{
	private static final double ONE_DEGREE_IN_RADIAN = Math.PI / 180;
	private static final double ONE_RADIAN_IN_DEGREE = 180 / Math.PI;

	public GeoLocationServiceImpl()
	{
		
	}

	@Override
	public boolean isValid(GeoLocation geoLocation) throws GeoLocationException
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public GeoLocation getGeoLocation(String ipAddress) throws GeoLocationException
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GeoLocation getGeoLocation(PhysicalAddress physicalAddress) throws GeoLocationException
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PhysicalAddress getPhysicalAddressFromGeoLocation(GeoLocation physicalAddress) throws GeoLocationException
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GeoLocation getGeoLocation(ZipCode zipCode) throws GeoLocationException
	{
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Taken from StackOverflow
	 * Center of geometry defined by coordinates 
	 * https://stackoverflow.com/questions/6671183/calculate-the-center-point-of-multiple-latitude-longitude-coordinate-pairs
	 */
	@Override
	public GeoLocation findCenterOf(GeoLocation...geoLocations) throws GeoLocationException
	{
		GeoLocation center = null;
		if (geoLocations.length == 1)
		{
			center = geoLocations[0];
		}
		else
		{
			double x = 0, y = 0, z = 0;

			for (GeoLocation geoLocation : geoLocations)
			{
				double latitude = geoLocation.getLatitude().getDecimalValue() * ONE_DEGREE_IN_RADIAN, longitude = geoLocation.getLongitude().getDecimalValue() * ONE_DEGREE_IN_RADIAN;
				double cosLatitude = Math.cos(latitude);// save it as we need it twice
				x += cosLatitude * Math.cos(longitude);
				y += cosLatitude * Math.sin(longitude);
				z += Math.sin(latitude);
			}

			int total = geoLocations.length;

			x = x / total;
			y = y / total;
			z = z / total;

			double centralLongitude = Math.atan2(y, x);
			double centralSquareRoot = Math.sqrt(x * x + y * y);
			double centralLatitude = Math.atan2(z, centralSquareRoot);

			center = new GeoLocation(centralLatitude * ONE_RADIAN_IN_DEGREE, centralLongitude * ONE_RADIAN_IN_DEGREE);
		}
		
		return center;
	}
}
