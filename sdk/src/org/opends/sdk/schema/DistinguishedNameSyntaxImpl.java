/*
 * CDDL HEADER START
 *
 * The contents of this file are subject to the terms of the
 * Common Development and Distribution License, Version 1.0 only
 * (the "License").  You may not use this file except in compliance
 * with the License.
 *
 * You can obtain a copy of the license at
 * trunk/opends/resource/legal-notices/OpenDS.LICENSE
 * or https://OpenDS.dev.java.net/OpenDS.LICENSE.
 * See the License for the specific language governing permissions
 * and limitations under the License.
 *
 * When distributing Covered Code, include this CDDL HEADER in each
 * file and include the License file at
 * trunk/opends/resource/legal-notices/OpenDS.LICENSE.  If applicable,
 * add the following below this CDDL HEADER, with the fields enclosed
 * by brackets "[]" replaced with your own identifying information:
 *      Portions Copyright [yyyy] [name of copyright owner]
 *
 * CDDL HEADER END
 *
 *
 *      Copyright 2009 Sun Microsystems, Inc.
 */

package org.opends.sdk.schema;



import static org.opends.sdk.schema.SchemaConstants.EMR_DN_OID;
import static org.opends.sdk.schema.SchemaConstants.SMR_CASE_IGNORE_OID;
import static org.opends.sdk.schema.SchemaConstants.SYNTAX_DN_NAME;

import org.opends.sdk.ByteSequence;
import org.opends.sdk.DN;
import org.opends.sdk.LocalizableMessageBuilder;
import org.opends.sdk.LocalizedIllegalArgumentException;



/**
 * This class defines the distinguished name attribute syntax, which is used for
 * attributes that hold distinguished names (DNs). Equality and substring
 * matching will be allowed by default.
 */
final class DistinguishedNameSyntaxImpl extends AbstractSyntaxImpl
{
  @Override
  public String getEqualityMatchingRule()
  {
    return EMR_DN_OID;
  }



  public String getName()
  {
    return SYNTAX_DN_NAME;
  }



  @Override
  public String getSubstringMatchingRule()
  {
    return SMR_CASE_IGNORE_OID;
  }



  public boolean isHumanReadable()
  {
    return true;
  }



  public boolean valueIsAcceptable(final Schema schema,
      final ByteSequence value, final LocalizableMessageBuilder invalidReason)
  {
    try
    {
      DN.valueOf(value.toString(), schema);
    }
    catch (final LocalizedIllegalArgumentException de)
    {
      invalidReason.append(de.getMessageObject());
      return false;
    }

    return true;
  }
}
