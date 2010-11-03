/*
 * JBoss, Home of Professional Open Source.
 * Copyright 2008, Red Hat Middleware LLC, and individual contributors
 * as indicated by the @author tags. See the copyright.txt file in the
 * distribution for a full listing of individual contributors. 
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package org.picketlink.identity.federation.core.parsers.saml;

import javax.xml.namespace.QName;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.StartElement;

import org.picketlink.identity.federation.core.exceptions.ParsingException;
import org.picketlink.identity.federation.core.parsers.util.StaxParserUtil; 
import org.picketlink.identity.federation.core.saml.v2.util.XMLTimeUtil;
import org.picketlink.identity.federation.saml.v2.protocol.RequestAbstractType;

/**
 * Base Class for SAML Request Parsing
 * @author Anil.Saldhana@redhat.com
 * @since Nov 2, 2010
 */
public abstract class SAMLRequestAbstractParser
{
   /**
    * Parse the attributes that are common to all SAML Request Types
    * @param startElement
    * @param request
    * @throws ParsingException
    */
   protected void parseBaseAttributes(  StartElement startElement , RequestAbstractType request ) throws ParsingException
   {
      Attribute idAttr = startElement.getAttributeByName( new QName( "ID" ));
      if( idAttr == null )
         throw new RuntimeException( "ID attribute is missing" );
      request.setID( StaxParserUtil.getAttributeValue( idAttr )); 
      
      Attribute version = startElement.getAttributeByName( new QName( "Version" ));
      if( version == null )
         throw new RuntimeException( "Version attribute required in Request" );
      request.setVersion( StaxParserUtil.getAttributeValue( version ));
      
      Attribute issueInstant = startElement.getAttributeByName( new QName( "IssueInstant" ));
      if( issueInstant == null )
         throw new RuntimeException( "IssueInstant attribute required in Request" ); 
      request.setIssueInstant( XMLTimeUtil.parse( StaxParserUtil.getAttributeValue( issueInstant ))); 
      
      Attribute destination = startElement.getAttributeByName( new QName( "Destination" ));
      if( destination != null )
         request.setDestination( StaxParserUtil.getAttributeValue( destination ));
      
      Attribute consent = startElement.getAttributeByName( new QName( "Consent" ));
      if( consent != null )
         request.setConsent( StaxParserUtil.getAttributeValue( consent )); 
   } 
}