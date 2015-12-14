package info.danbecker.rx;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;

import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

import org.mockito.Mockito;

import static org.mockito.Mockito.mock;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import rx.subjects.PublishSubject;

public class BufferedReaderIteratorTest {
	@Before
    public void setup() {
	}
	
	@Test
	public void testIterator() {
		try {
			int lines = 0;
			BufferedReaderIterator bri = new BufferedReaderIterator( new File( "src/main/resources/GettysburgAddress.txt" ) );
			Iterator<String> iter = bri.iterator();
			while ( iter.hasNext() ) {
				System.out.println( "iter=" + iter.next());
				lines++;
			}
			assertEquals( "Line count", 26 , lines);
		} catch ( FileNotFoundException e ) {
			System.out.println( "Exception e=" + e );
			fail();
		}
	}
	
	@Test
	public void testObservable() {
		try {
			int lines = 0;
			BufferedReaderIterator bri = new BufferedReaderIterator( new File( "src/main/resources/GettysburgAddress.txt" ) );

	        assertEquals("line Count", new Integer(26), Observable.from(bri).count().toBlocking().single());			
		} catch ( FileNotFoundException e ) {
			System.out.println( "Exception e=" + e );
			fail();
		}
	}
	
//	@Test
//	public void testPublishSubjectCompleted() {
//		PublishSubject<String> subject = PublishSubject.create();
//
//		@SuppressWarnings("unchecked")
//		Observer<String> observer = mock(Observer.class);
//		subject.subscribe(observer);
//
//		subject.onNext("one");
//		subject.onNext("two");
//		subject.onNext("three");
//		subject.onCompleted();
//
//		@SuppressWarnings("unchecked")
//		Observer<String> anotherObserver = mock(Observer.class);
//		subject.subscribe(anotherObserver);
//
//		subject.onNext("four");
//		subject.onCompleted();
//		subject.onError(new Throwable());
//
//		// Assert counts
//		verify(observer, times(1)).onNext("one");
//		verify(observer, times(1)).onNext("two");
//		verify(observer, times(1)).onNext("three");
//		verify(observer, Mockito.never()).onError(any(Throwable.class));
//		verify(observer, times(1)).onCompleted();
//	}
}