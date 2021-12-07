import java.io.File
import java.math.BigInteger
import java.security.MessageDigest

/**
 * Reads lines from the given input txt file.
 */
fun readInputLines(name: String) = File("src", "$name.txt").readLines()

fun readInputFile(name: String) = File("src", "$name.txt").readText()

/**
 * Reads integer lines from the given input txt file.
 */
fun readInputNumbers(name: String) = readInputLines(name).map { it.toInt() }

fun readInputSplitNumber(name: String) = readInputFile(name).split(",").map { it.toInt() }

/**
 * Converts string to md5 hash.
 */
fun String.md5(): String = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray())).toString(16)
