package com.vexigo.prediction.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.nio.channels.Channels;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.Objects;

import com.vexigo.prediction.datatypes.consts.FileConsts;

public class FileUtils 
{

	/**
	 * Reads all the bytes from a file. The method ensures that the file is
	 * closed when all bytes have been read or an I/O error, or other runtime
	 * exception, is thrown.
	 *
	 * <p> Note that this method is intended for simple cases where it is
	 * convenient to read all bytes into a byte array. It is not intended for
	 * reading in large files.
	 *
	 * @param   path
	 *          the path to the file
	 *
	 * @return  a byte array containing the bytes read from the file
	 *
	 * @throws  IOException
	 *          if an I/O error occurs reading from the stream
	 * @throws  OutOfMemoryError
	 *          if an array of the required size cannot be allocated, for
	 *          example the file is larger that {@code 2GB}
	 * @throws  SecurityException
	 *          In the case of the default provider, and a security manager is
	 *          installed, the {@link SecurityManager#checkRead(String) checkRead}
	 *          method is invoked to check read access to the file.
	 */
	public static byte[] readAllBytes(InputStream in) throws IOException 
	{
		return read(in, (int)FileConsts.BUFFER_SIZE);
	}

	/**
	 * 
	 */
	public static InputStream getInputStreamFromPath(String path) throws IOException 
	{
		InputStream inputStream = new FileInputStream(new File(FileConsts.PROJECT_PATH_LOCATIOIN + path));
		return inputStream;    
	}






	/**
	 * Reads all the bytes from an input stream. Uses {@code initialSize} as a hint
	 * about how many bytes the stream will have.
	 *
	 * @param   source
	 *          the input stream to read from
	 * @param   initialSize
	 *          the initial size of the byte array to allocate
	 *
	 * @return  a byte array containing the bytes read from the file
	 *
	 * @throws  IOException
	 *          if an I/O error occurs reading from the stream
	 * @throws  OutOfMemoryError
	 *          if an array of the required size cannot be allocated
	 */
	private static byte[] read(InputStream source, int initialSize)
			throws IOException
	{
		int capacity = initialSize;
		byte[] buf = new byte[capacity];
		int nread = 0;
		int n;
		for (;;) {
			// read to EOF which may read more or less than initialSize (eg: file
			// is truncated while we are reading)
			while ((n = source.read(buf, nread, capacity - nread)) > 0)
				nread += n;

			// if last call to source.read() returned -1, we are done
			// otherwise, try to read one more byte; if that failed we're done too
			if (n < 0 || (n = source.read()) < 0)
				break;

			// one more byte was read; need to allocate a larger buffer
			if (capacity <= FileConsts.MAX_BUFFER_SIZE - capacity) {
				capacity = Math.max(capacity << 1, FileConsts.BUFFER_SIZE);
			} else {
				if (capacity == FileConsts.MAX_BUFFER_SIZE)
					throw new OutOfMemoryError("Required array size too large");
				capacity = FileConsts.MAX_BUFFER_SIZE;
			}
			buf = Arrays.copyOf(buf, capacity);
			buf[nread++] = (byte)n;
		}
		return (capacity == nread) ? buf : Arrays.copyOf(buf, nread);
	}


	/**
	 * Writes bytes to a file. The {@code options} parameter specifies how the
	 * the file is created or opened. If no options are present then this method
	 * works as if the {@link StandardOpenOption#CREATE CREATE}, {@link
	 * StandardOpenOption#TRUNCATE_EXISTING TRUNCATE_EXISTING}, and {@link
	 * StandardOpenOption#WRITE WRITE} options are present. In other words, it
	 * opens the file for writing, creating the file if it doesn't exist, or
	 * initially truncating an existing {@link #isRegularFile regular-file} to
	 * a size of {@code 0}. All bytes in the byte array are written to the file.
	 * The method ensures that the file is closed when all bytes have been
	 * written (or an I/O error or other runtime exception is thrown). If an I/O
	 * error occurs then it may do so after the file has created or truncated,
	 * or after some bytes have been written to the file.
	 *
	 * <p> <b>Usage example</b>: By default the method creates a new file or
	 * overwrites an existing file. Suppose you instead want to append bytes
	 * to an existing file:
	 * <pre>
	 *     Path path = ...
	 *     byte[] bytes = ...
	 *     Files.write(path, bytes, StandardOpenOption.APPEND);
	 * </pre>
	 *
	 * @param   path
	 *          the path to the file
	 * @param   bytes
	 *          the byte array with the bytes to write
	 * @param   options
	 *          options specifying how the file is opened
	 *
	 * @return  the path
	 *
	 * @throws  IOException
	 *          if an I/O error occurs writing to or creating the file
	 * @throws  UnsupportedOperationException
	 *          if an unsupported option is specified
	 * @throws  SecurityException
	 *          In the case of the default provider, and a security manager is
	 *          installed, the {@link SecurityManager#checkWrite(String) checkWrite}
	 *          method is invoked to check write access to the file.
	 */
	public static String write(String path, byte[] bytes)
			throws IOException
	{
		// ensure bytes is not null before opening file
		Objects.requireNonNull(bytes);
		   
	    //convert array of bytes into file
//	    FileOutputStream fileOuputStream = new FileOutputStream(path); 
//	    fileOuputStream.write(bFile);
//	    fileOuputStream.close();
//
//		try (OutputStream out = Files.newOutputStream(path)) {
//			int len = bytes.length;
//			int rem = len;
//			while (rem > 0) {
//				int n = Math.min(rem, FileConsts.BUFFER_SIZE);
//				out.write(bytes, (len-rem), n);
//				rem -= n;
//			}
//		}
		return path;
	}



}
