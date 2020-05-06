package com.kafka.test.crypto

import java.util.Base64
import java.util.Base64._

import javax.crypto.Cipher
import javax.crypto.Cipher._
import javax.crypto.spec.{IvParameterSpec, SecretKeySpec}

object Encrypter {

  private val salt = "DxVnlUlQSu3E5acRu7HPwg=="
  private val cipherName = "AES"
  private val cipherMode = "CBC"
  private val charset = "utf-8"
  private val Algorithm = s"$cipherName/$cipherMode/PKCS5Padding"

  private val Key = new SecretKeySpec(getDecoder.decode(salt), cipherName)
  private val IvSpec = new IvParameterSpec(new Array[Byte](16))

  private val cipher = (mode: Int)=> {
    val cipher = getInstance(Algorithm)
    cipher.init(mode, Key, IvSpec)
    cipher
  }
  private val toBytes = (in: String) => in.getBytes(charset)
  private val base64Encode = (toEnc: Array[Byte]) => getEncoder.encode(toEnc)
  private val base64Decode = (toDec: Array[Byte]) => getDecoder.decode(toDec)

  def encrypt(text: String): String =
    new String(base64Encode(cipher(ENCRYPT_MODE) doFinal toBytes(text)), charset)

  def decrypt(text: String): String = {
    new String(cipher(DECRYPT_MODE) doFinal base64Decode(toBytes(text)), charset)
  }
}
