package org.nick.abe;

import java.security.Security;

import org.bouncycastle.jce.provider.BouncyCastleProvider;


public class Main {

	public static void main ( String[] args ) {
		Security.addProvider ( new BouncyCastleProvider () );

		if ( args.length < 3 ) {
			usage ();

			System.exit ( 1 );
		}

		String mode = args[0];
		if ( !"pack".equals ( mode ) && !"unpack".equals ( mode ) && !"unpackex".equals(mode)) {
			usage ();

			System.exit ( 1 );
		}

		boolean unpack = "unpack".equals ( mode );
		boolean unpackex = "unpackex".equals(mode);
		String backupFilename = unpack || unpackex ? args[1] : args[2];
		String tarFilename = unpack || unpackex ? args[2] : args[1];
		String password = null;
		if ( args.length > 3 ) {
			password = args[3];
		}

		if ( unpack ) {
			System.out.println("unpack");
			AndroidBackup.extractAsTar ( backupFilename, tarFilename, password );
		} else if ( unpackex ) {
			System.out.println("unpackex");
			AndroidBackup.unpackex ( backupFilename, tarFilename, password );
		} else {
			AndroidBackup.packTar ( tarFilename, backupFilename, password );
		}
	}

    private static void usage ( ) {
		System.out.println ( "Android backup extractor" );
		System.out.println ( "Usage:" );
		System.out
				.println ( "\tunpack:\tabe unpack <backup.ab> <backup.tar> [password]" );
		System.out
				.println ( "\tunpackex:\tabe unpack <backup.abex> <backup.tar> [password]" );
		System.out
				.println ( "\tpack:\tabe pack <backup.tar> <backup.ab> [password]" );

	}

}
