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
 *      Copyright 2010 Sun Microsystems, Inc.
 */

package org.opends.sdk.requests;



import javax.net.ssl.SSLContext;

import org.opends.sdk.ByteString;
import org.opends.sdk.DecodeException;
import org.opends.sdk.DecodeOptions;
import org.opends.sdk.ResultCode;
import org.opends.sdk.controls.Control;
import org.opends.sdk.responses.ExtendedResult;
import org.opends.sdk.responses.ExtendedResultDecoder;
import org.opends.sdk.responses.GenericExtendedResult;
import org.opends.sdk.responses.Responses;

import com.sun.opends.sdk.util.Validator;



/**
 * Start TLS extended request implementation.
 */
final class StartTLSExtendedRequestImpl extends
    AbstractExtendedRequest<StartTLSExtendedRequest, ExtendedResult> implements
    StartTLSExtendedRequest
{
  static final class RequestDecoder implements
      ExtendedRequestDecoder<StartTLSExtendedRequest, ExtendedResult>
  {

    public StartTLSExtendedRequest decodeExtendedRequest(
        final ExtendedRequest<?> request, final DecodeOptions options)
        throws DecodeException
    {
      // TODO: Check the OID and that the value is not present.
      final StartTLSExtendedRequest newRequest = new StartTLSExtendedRequestImpl();
      for (final Control control : request.getControls())
      {
        newRequest.addControl(control);
      }
      return newRequest;
    }
  }



  private static final class ResultDecoder implements
      ExtendedResultDecoder<ExtendedResult>
  {
    public GenericExtendedResult adaptExtendedErrorResult(
        final ResultCode resultCode, final String matchedDN,
        final String diagnosticMessage)
    {
      return Responses.newGenericExtendedResult(resultCode).setMatchedDN(
          matchedDN).setDiagnosticMessage(diagnosticMessage);
    }



    public ExtendedResult decodeExtendedResult(final ExtendedResult result,
        final DecodeOptions options) throws DecodeException
    {
      // TODO: Should we check oid is NOT null and matches but
      // value is null?
      return result;
    }
  }



  private SSLContext sslContext;

  // No need to expose this.
  private static final ExtendedResultDecoder<ExtendedResult> RESULT_DECODER = new ResultDecoder();



  StartTLSExtendedRequestImpl(final SSLContext sslContext)
  {
    Validator.ensureNotNull(sslContext);
    this.sslContext = sslContext;
  }



  // Prevent instantiation.
  private StartTLSExtendedRequestImpl()
  {
    // Nothing to do.
  }



  /**
   * {@inheritDoc}
   */
  @Override
  public String getOID()
  {
    return OID;
  }



  /**
   * {@inheritDoc}
   */
  @Override
  public ExtendedResultDecoder<ExtendedResult> getResultDecoder()
  {
    return RESULT_DECODER;
  }



  /**
   * {@inheritDoc}
   */
  public SSLContext getSSLContext()
  {
    return sslContext;
  }



  /**
   * {@inheritDoc}
   */
  @Override
  public ByteString getValue()
  {
    return null;
  }



  /**
   * {@inheritDoc}
   */
  @Override
  public boolean hasValue()
  {
    return false;
  }



  /**
   * {@inheritDoc}
   */
  public StartTLSExtendedRequest setSSLContext(final SSLContext sslContext)
  {
    Validator.ensureNotNull(sslContext);
    this.sslContext = sslContext;
    return this;
  }



  /**
   * {@inheritDoc}
   */
  @Override
  public String toString()
  {
    final StringBuilder builder = new StringBuilder();
    builder.append("StartTLSExtendedRequest(requestName=");
    builder.append(getOID());
    builder.append(", controls=");
    builder.append(getControls());
    builder.append(")");
    return builder.toString();
  }

}
