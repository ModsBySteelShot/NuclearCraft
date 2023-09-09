package nc.util;

import java.util.Objects;
import java.util.function.*;

public class PrimitiveFunction {
	
	@FunctionalInterface
	public static interface ByteSupplier {
		
		byte getAsByte();
	}
	
	@FunctionalInterface
	public static interface ShortSupplier {
		
		short getAsShort();
	}
	
	@FunctionalInterface
	public static interface FloatSupplier {
		
		float getAsFloat();
	}
	
	@FunctionalInterface
	public static interface CharSupplier {
		
		char getAsChar();
	}
	
	@FunctionalInterface
	public static interface ByteConsumer {
		
		void accept(byte value);
		
		default ByteConsumer andThen(ByteConsumer after) {
			Objects.requireNonNull(after);
			return value -> {
				accept(value);
				after.accept(value);
			};
		}
	}
	
	@FunctionalInterface
	public static interface ShortConsumer {
		
		void accept(short value);
		
		default ShortConsumer andThen(ShortConsumer after) {
			Objects.requireNonNull(after);
			return value -> {
				accept(value);
				after.accept(value);
			};
		}
	}
	
	@FunctionalInterface
	public static interface FloatConsumer {
		
		void accept(float value);
		
		default FloatConsumer andThen(FloatConsumer after) {
			Objects.requireNonNull(after);
			return value -> {
				accept(value);
				after.accept(value);
			};
		}
	}
	
	@FunctionalInterface
	public static interface BooleanConsumer {
		
		void accept(boolean value);
		
		default BooleanConsumer andThen(BooleanConsumer after) {
			Objects.requireNonNull(after);
			return value -> {
				accept(value);
				after.accept(value);
			};
		}
	}
	
	@FunctionalInterface
	public static interface CharConsumer {
		
		void accept(char value);
		
		default CharConsumer andThen(CharConsumer after) {
			Objects.requireNonNull(after);
			return value -> {
				accept(value);
				after.accept(value);
			};
		}
	}
	
	@FunctionalInterface
	public static interface BytePredicate {
		
		boolean test(byte value);
		
		default BytePredicate and(BytePredicate other) {
			Objects.requireNonNull(other);
			return value -> test(value) && other.test(value);
		}
		
		default BytePredicate negate() {
			return value -> !test(value);
		}
		
		default BytePredicate or(BytePredicate other) {
			Objects.requireNonNull(other);
			return value -> test(value) || other.test(value);
		}
	}
	
	@FunctionalInterface
	public static interface ShortPredicate {
		
		boolean test(short value);
		
		default ShortPredicate and(ShortPredicate other) {
			Objects.requireNonNull(other);
			return value -> test(value) && other.test(value);
		}
		
		default ShortPredicate negate() {
			return value -> !test(value);
		}
		
		default ShortPredicate or(ShortPredicate other) {
			Objects.requireNonNull(other);
			return value -> test(value) || other.test(value);
		}
	}
	
	@FunctionalInterface
	public static interface FloatPredicate {
		
		boolean test(float value);
		
		default FloatPredicate and(FloatPredicate other) {
			Objects.requireNonNull(other);
			return value -> test(value) && other.test(value);
		}
		
		default FloatPredicate negate() {
			return value -> !test(value);
		}
		
		default FloatPredicate or(FloatPredicate other) {
			Objects.requireNonNull(other);
			return value -> test(value) || other.test(value);
		}
	}
	
	@FunctionalInterface
	public static interface BooleanPredicate {
		
		boolean test(boolean value);
		
		default BooleanPredicate and(BooleanPredicate other) {
			Objects.requireNonNull(other);
			return value -> test(value) && other.test(value);
		}
		
		default BooleanPredicate negate() {
			return value -> !test(value);
		}
		
		default BooleanPredicate or(BooleanPredicate other) {
			Objects.requireNonNull(other);
			return value -> test(value) || other.test(value);
		}
	}
	
	@FunctionalInterface
	public static interface CharPredicate {
		
		boolean test(char value);
		
		default CharPredicate and(CharPredicate other) {
			Objects.requireNonNull(other);
			return value -> test(value) && other.test(value);
		}
		
		default CharPredicate negate() {
			return value -> !test(value);
		}
		
		default CharPredicate or(CharPredicate other) {
			Objects.requireNonNull(other);
			return value -> test(value) || other.test(value);
		}
	}
	
	@FunctionalInterface
	public static interface ByteFunction<R> {
		
		R apply(byte value);
	}
	
	@FunctionalInterface
	public static interface ShortFunction<R> {
		
		R apply(short value);
	}
	
	@FunctionalInterface
	public static interface FloatFunction<R> {
		
		R apply(float value);
	}
	
	@FunctionalInterface
	public static interface BooleanFunction<R> {
		
		R apply(boolean value);
	}
	
	@FunctionalInterface
	public static interface CharFunction<R> {
		
		R apply(char value);
	}
	
	@FunctionalInterface
	public static interface ToByteFunction<T> {
		
		byte applyAsByte(T value);
	}
	
	@FunctionalInterface
	public static interface ToShortFunction<T> {
		
		short applyAsShort(T value);
	}
	
	@FunctionalInterface
	public static interface ToFloatFunction<T> {
		
		float applyAsFloat(T value);
	}
	
	@FunctionalInterface
	public static interface ToBooleanFunction<T> {
		
		boolean applyAsBoolean(T value);
	}
	
	@FunctionalInterface
	public static interface ToCharFunction<T> {
		
		char applyAsChar(T value);
	}
	
	@FunctionalInterface
	public static interface ToByteBiFunction<T, U> {
		
		byte applyAsByte(T t, U u);
	}
	
	@FunctionalInterface
	public static interface ToShortBiFunction<T, U> {
		
		short applyAsShort(T t, U u);
	}
	
	@FunctionalInterface
	public static interface ToFloatBiFunction<T, U> {
		
		float applyAsFloat(T t, U u);
	}
	
	@FunctionalInterface
	public static interface ToBooleanBiFunction<T, U> {
		
		boolean applyAsBoolean(T t, U u);
	}
	
	@FunctionalInterface
	public static interface ToCharBiFunction<T, U> {
		
		char applyAsChar(T t, U u);
	}
	
	@FunctionalInterface
	public static interface ByteUnaryOperator {
		
		byte applyAsByte(byte operand);
		
		default ByteUnaryOperator compose(ByteUnaryOperator before) {
			Objects.requireNonNull(before);
			return value -> applyAsByte(before.applyAsByte(value));
		}
		
		default ByteUnaryOperator andThen(ByteUnaryOperator after) {
			Objects.requireNonNull(after);
			return value -> after.applyAsByte(applyAsByte(value));
		}
		
		static ByteUnaryOperator identity() {
			return value -> value;
		}
	}
	
	@FunctionalInterface
	public static interface ShortUnaryOperator {
		
		short applyAsShort(short operand);
		
		default ShortUnaryOperator compose(ShortUnaryOperator before) {
			Objects.requireNonNull(before);
			return value -> applyAsShort(before.applyAsShort(value));
		}
		
		default ShortUnaryOperator andThen(ShortUnaryOperator after) {
			Objects.requireNonNull(after);
			return value -> after.applyAsShort(applyAsShort(value));
		}
		
		static ShortUnaryOperator identity() {
			return value -> value;
		}
	}
	
	@FunctionalInterface
	public static interface FloatUnaryOperator {
		
		float applyAsFloat(float operand);
		
		default FloatUnaryOperator compose(FloatUnaryOperator before) {
			Objects.requireNonNull(before);
			return value -> applyAsFloat(before.applyAsFloat(value));
		}
		
		default FloatUnaryOperator andThen(FloatUnaryOperator after) {
			Objects.requireNonNull(after);
			return value -> after.applyAsFloat(applyAsFloat(value));
		}
		
		static FloatUnaryOperator identity() {
			return value -> value;
		}
	}
	
	@FunctionalInterface
	public static interface BooleanUnaryOperator {
		
		boolean applyAsBoolean(boolean operand);
		
		default BooleanUnaryOperator compose(BooleanUnaryOperator before) {
			Objects.requireNonNull(before);
			return value -> applyAsBoolean(before.applyAsBoolean(value));
		}
		
		default BooleanUnaryOperator andThen(BooleanUnaryOperator after) {
			Objects.requireNonNull(after);
			return value -> after.applyAsBoolean(applyAsBoolean(value));
		}
		
		static BooleanUnaryOperator identity() {
			return value -> value;
		}
	}
	
	@FunctionalInterface
	public static interface CharUnaryOperator {
		
		char applyAsChar(char operand);
		
		default CharUnaryOperator compose(CharUnaryOperator before) {
			Objects.requireNonNull(before);
			return value -> applyAsChar(before.applyAsChar(value));
		}
		
		default CharUnaryOperator andThen(CharUnaryOperator after) {
			Objects.requireNonNull(after);
			return value -> after.applyAsChar(applyAsChar(value));
		}
		
		static CharUnaryOperator identity() {
			return value -> value;
		}
	}
	
	@FunctionalInterface
	public static interface ByteBinaryOperator {
		
		byte applyAsByte(byte left, byte right);
	}
	
	@FunctionalInterface
	public static interface ShortBinaryOperator {
		
		short applyAsShort(short left, short right);
	}
	
	@FunctionalInterface
	public static interface FloatBinaryOperator {
		
		float applyAsFloat(float left, float right);
	}
	
	@FunctionalInterface
	public static interface BooleanBinaryOperator {
		
		boolean applyAsBoolean(boolean left, boolean right);
	}
	
	@FunctionalInterface
	public static interface CharBinaryOperator {
		
		char applyAsChar(char left, char right);
	}
	
	@FunctionalInterface
	public static interface ObjByteConsumer<T> {
		
		void accept(T t, byte value);
	}
	
	@FunctionalInterface
	public static interface ObjShortConsumer<T> {
		
		void accept(T t, short value);
	}
	
	@FunctionalInterface
	public static interface ObjFloatConsumer<T> {
		
		void accept(T t, float value);
	}
	
	@FunctionalInterface
	public static interface ObjBooleanConsumer<T> {
		
		void accept(T t, boolean value);
	}
	
	@FunctionalInterface
	public static interface ObjCharConsumer<T> {
		
		void accept(T t, char value);
	}
	
	@FunctionalInterface
	public static interface ByteObjConsumer<T> {
		
		void accept(byte value, T t);
	}
	
	@FunctionalInterface
	public static interface ShortObjConsumer<T> {
		
		void accept(short value, T t);
	}
	
	@FunctionalInterface
	public static interface IntObjConsumer<T> {
		
		void accept(int value, T t);
	}
	
	@FunctionalInterface
	public static interface LongObjConsumer<T> {
		
		void accept(long value, T t);
	}
	
	@FunctionalInterface
	public static interface FloatObjConsumer<T> {
		
		void accept(float value, T t);
	}
	
	@FunctionalInterface
	public static interface DoubleObjConsumer<T> {
		
		void accept(double value, T t);
	}
	
	@FunctionalInterface
	public static interface BooleanObjConsumer<T> {
		
		void accept(boolean value, T t);
	}
	
	@FunctionalInterface
	public static interface CharObjConsumer<T> {
		
		void accept(char value, T t);
	}
	
	@FunctionalInterface
	public static interface ObjByteFunction<T, R> {
		
		R apply(T t, byte value);
		
		default <V> ObjByteFunction<T, V> andThen(Function<? super R, ? extends V> after) {
			Objects.requireNonNull(after);
			return (t, value) -> after.apply(apply(t, value));
		}
	}
	
	@FunctionalInterface
	public static interface ObjShortFunction<T, R> {
		
		R apply(T t, short value);
		
		default <V> ObjShortFunction<T, V> andThen(Function<? super R, ? extends V> after) {
			Objects.requireNonNull(after);
			return (t, value) -> after.apply(apply(t, value));
		}
	}
	
	@FunctionalInterface
	public static interface ObjIntFunction<T, R> {
		
		R apply(T t, int value);
		
		default <V> ObjIntFunction<T, V> andThen(Function<? super R, ? extends V> after) {
			Objects.requireNonNull(after);
			return (t, value) -> after.apply(apply(t, value));
		}
	}
	
	@FunctionalInterface
	public static interface ObjLongFunction<T, R> {
		
		R apply(T t, long value);
		
		default <V> ObjLongFunction<T, V> andThen(Function<? super R, ? extends V> after) {
			Objects.requireNonNull(after);
			return (t, value) -> after.apply(apply(t, value));
		}
	}
	
	@FunctionalInterface
	public static interface ObjFloatFunction<T, R> {
		
		R apply(T t, float value);
		
		default <V> ObjFloatFunction<T, V> andThen(Function<? super R, ? extends V> after) {
			Objects.requireNonNull(after);
			return (t, value) -> after.apply(apply(t, value));
		}
	}
	
	@FunctionalInterface
	public static interface ObjBooleanFunction<T, R> {
		
		R apply(T t, boolean value);
		
		default <V> ObjBooleanFunction<T, V> andThen(Function<? super R, ? extends V> after) {
			Objects.requireNonNull(after);
			return (t, value) -> after.apply(apply(t, value));
		}
	}
	
	@FunctionalInterface
	public static interface ObjCharFunction<T, R> {
		
		R apply(T t, char value);
		
		default <V> ObjCharFunction<T, V> andThen(Function<? super R, ? extends V> after) {
			Objects.requireNonNull(after);
			return (t, value) -> after.apply(apply(t, value));
		}
	}
	
	@FunctionalInterface
	public static interface ByteObjFunction<T, R> {
		
		R apply(byte value, T t);
		
		default <V> ByteObjFunction<T, V> andThen(Function<? super R, ? extends V> after) {
			Objects.requireNonNull(after);
			return (value, t) -> after.apply(apply(value, t));
		}
	}
	
	@FunctionalInterface
	public static interface ShortObjFunction<T, R> {
		
		R apply(short value, T t);
		
		default <V> ShortObjFunction<T, V> andThen(Function<? super R, ? extends V> after) {
			Objects.requireNonNull(after);
			return (value, t) -> after.apply(apply(value, t));
		}
	}
	
	@FunctionalInterface
	public static interface IntObjFunction<T, R> {
		
		R apply(int value, T t);
		
		default <V> IntObjFunction<T, V> andThen(Function<? super R, ? extends V> after) {
			Objects.requireNonNull(after);
			return (value, t) -> after.apply(apply(value, t));
		}
	}
	
	@FunctionalInterface
	public static interface LongObjFunction<T, R> {
		
		R apply(long value, T t);
		
		default <V> LongObjFunction<T, V> andThen(Function<? super R, ? extends V> after) {
			Objects.requireNonNull(after);
			return (value, t) -> after.apply(apply(value, t));
		}
	}
	
	@FunctionalInterface
	public static interface FloatObjFunction<T, R> {
		
		R apply(float value, T t);
		
		default <V> FloatObjFunction<T, V> andThen(Function<? super R, ? extends V> after) {
			Objects.requireNonNull(after);
			return (value, t) -> after.apply(apply(value, t));
		}
	}
	
	@FunctionalInterface
	public static interface DoubleObjFunction<T, R> {
		
		R apply(double value, T t);
		
		default <V> DoubleObjFunction<T, V> andThen(Function<? super R, ? extends V> after) {
			Objects.requireNonNull(after);
			return (value, t) -> after.apply(apply(value, t));
		}
	}
	
	@FunctionalInterface
	public static interface BooleanObjFunction<T, R> {
		
		R apply(boolean value, T t);
		
		default <V> BooleanObjFunction<T, V> andThen(Function<? super R, ? extends V> after) {
			Objects.requireNonNull(after);
			return (value, t) -> after.apply(apply(value, t));
		}
	}
	
	@FunctionalInterface
	public static interface CharObjFunction<T, R> {
		
		R apply(char value, T t);
		
		default <V> CharObjFunction<T, V> andThen(Function<? super R, ? extends V> after) {
			Objects.requireNonNull(after);
			return (value, t) -> after.apply(apply(value, t));
		}
	}
}
