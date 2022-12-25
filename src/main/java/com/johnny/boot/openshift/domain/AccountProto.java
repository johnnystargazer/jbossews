// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: Account.proto

package com.johnny.boot.openshift.domain;

public final class AccountProto {
	private AccountProto() {
	}

	public static void registerAllExtensions(
			com.google.protobuf.ExtensionRegistry registry) {
	}

	public interface AccountOrBuilder extends
			com.google.protobuf.MessageOrBuilder {

		// required int64 id = 1;
		/**
		 * <code>required int64 id = 1;</code>
		 */
		boolean hasId();

		/**
		 * <code>required int64 id = 1;</code>
		 */
		long getId();

		// required string name = 2;
		/**
		 * <code>required string name = 2;</code>
		 */
		boolean hasName();

		/**
		 * <code>required string name = 2;</code>
		 */
		java.lang.String getName();

		/**
		 * <code>required string name = 2;</code>
		 */
		com.google.protobuf.ByteString getNameBytes();

		// required int64 parentId = 3;
		/**
		 * <code>required int64 parentId = 3;</code>
		 */
		boolean hasParentId();

		/**
		 * <code>required int64 parentId = 3;</code>
		 */
		long getParentId();

		// required .common.Status status = 4;
		/**
		 * <code>required .common.Status status = 4;</code>
		 */
		boolean hasStatus();

		/**
		 * <code>required .common.Status status = 4;</code>
		 */
		com.johnny.boot.openshift.domain.TypeProtos.Status getStatus();
	}

	/**
	 * Protobuf type {@code common.Account}
	 */
	public static final class Account extends
			com.google.protobuf.GeneratedMessage implements AccountOrBuilder {
		// Use Account.newBuilder() to construct.
		private Account(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
			super(builder);
			this.unknownFields = builder.getUnknownFields();
		}

		private Account(boolean noInit) {
			this.unknownFields = com.google.protobuf.UnknownFieldSet
					.getDefaultInstance();
		}

		private static final Account defaultInstance;

		public static Account getDefaultInstance() {
			return defaultInstance;
		}

		public Account getDefaultInstanceForType() {
			return defaultInstance;
		}

		private final com.google.protobuf.UnknownFieldSet unknownFields;

		@java.lang.Override
		public final com.google.protobuf.UnknownFieldSet getUnknownFields() {
			return this.unknownFields;
		}

		private Account(com.google.protobuf.CodedInputStream input,
				com.google.protobuf.ExtensionRegistryLite extensionRegistry)
				throws com.google.protobuf.InvalidProtocolBufferException {
			initFields();
			int mutable_bitField0_ = 0;
			com.google.protobuf.UnknownFieldSet.Builder unknownFields = com.google.protobuf.UnknownFieldSet
					.newBuilder();
			try {
				boolean done = false;
				while (!done) {
					int tag = input.readTag();
					switch (tag) {
					case 0:
						done = true;
						break;
					default: {
						if (!parseUnknownField(input, unknownFields,
								extensionRegistry, tag)) {
							done = true;
						}
						break;
					}
					case 8: {
						bitField0_ |= 0x00000001;
						id_ = input.readInt64();
						break;
					}
					case 18: {
						bitField0_ |= 0x00000002;
						name_ = input.readBytes();
						break;
					}
					case 24: {
						bitField0_ |= 0x00000004;
						parentId_ = input.readInt64();
						break;
					}
					case 32: {
						int rawValue = input.readEnum();
						com.johnny.boot.openshift.domain.TypeProtos.Status value = com.johnny.boot.openshift.domain.TypeProtos.Status
								.valueOf(rawValue);
						if (value == null) {
							unknownFields.mergeVarintField(4, rawValue);
						} else {
							bitField0_ |= 0x00000008;
							status_ = value;
						}
						break;
					}
					}
				}
			} catch (com.google.protobuf.InvalidProtocolBufferException e) {
				throw e.setUnfinishedMessage(this);
			} catch (java.io.IOException e) {
				throw new com.google.protobuf.InvalidProtocolBufferException(
						e.getMessage()).setUnfinishedMessage(this);
			} finally {
				this.unknownFields = unknownFields.build();
				makeExtensionsImmutable();
			}
		}

		public static final com.google.protobuf.Descriptors.Descriptor getDescriptor() {
			return com.johnny.boot.openshift.domain.AccountProto.internal_static_common_Account_descriptor;
		}

		protected com.google.protobuf.GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
			return com.johnny.boot.openshift.domain.AccountProto.internal_static_common_Account_fieldAccessorTable
					.ensureFieldAccessorsInitialized(
							com.johnny.boot.openshift.domain.AccountProto.Account.class,
							com.johnny.boot.openshift.domain.AccountProto.Account.Builder.class);
		}

		public static com.google.protobuf.Parser<Account> PARSER = new com.google.protobuf.AbstractParser<Account>() {
			public Account parsePartialFrom(
					com.google.protobuf.CodedInputStream input,
					com.google.protobuf.ExtensionRegistryLite extensionRegistry)
					throws com.google.protobuf.InvalidProtocolBufferException {
				return new Account(input, extensionRegistry);
			}
		};

		@java.lang.Override
		public com.google.protobuf.Parser<Account> getParserForType() {
			return PARSER;
		}

		private int bitField0_;
		// required int64 id = 1;
		public static final int ID_FIELD_NUMBER = 1;
		private long id_;

		/**
		 * <code>required int64 id = 1;</code>
		 */
		public boolean hasId() {
			return ((bitField0_ & 0x00000001) == 0x00000001);
		}

		/**
		 * <code>required int64 id = 1;</code>
		 */
		public long getId() {
			return id_;
		}

		// required string name = 2;
		public static final int NAME_FIELD_NUMBER = 2;
		private java.lang.Object name_;

		/**
		 * <code>required string name = 2;</code>
		 */
		public boolean hasName() {
			return ((bitField0_ & 0x00000002) == 0x00000002);
		}

		/**
		 * <code>required string name = 2;</code>
		 */
		public java.lang.String getName() {
			java.lang.Object ref = name_;
			if (ref instanceof java.lang.String) {
				return (java.lang.String) ref;
			} else {
				com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
				java.lang.String s = bs.toStringUtf8();
				if (bs.isValidUtf8()) {
					name_ = s;
				}
				return s;
			}
		}

		/**
		 * <code>required string name = 2;</code>
		 */
		public com.google.protobuf.ByteString getNameBytes() {
			java.lang.Object ref = name_;
			if (ref instanceof java.lang.String) {
				com.google.protobuf.ByteString b = com.google.protobuf.ByteString
						.copyFromUtf8((java.lang.String) ref);
				name_ = b;
				return b;
			} else {
				return (com.google.protobuf.ByteString) ref;
			}
		}

		// required int64 parentId = 3;
		public static final int PARENTID_FIELD_NUMBER = 3;
		private long parentId_;

		/**
		 * <code>required int64 parentId = 3;</code>
		 */
		public boolean hasParentId() {
			return ((bitField0_ & 0x00000004) == 0x00000004);
		}

		/**
		 * <code>required int64 parentId = 3;</code>
		 */
		public long getParentId() {
			return parentId_;
		}

		// required .common.Status status = 4;
		public static final int STATUS_FIELD_NUMBER = 4;
		private com.johnny.boot.openshift.domain.TypeProtos.Status status_;

		/**
		 * <code>required .common.Status status = 4;</code>
		 */
		public boolean hasStatus() {
			return ((bitField0_ & 0x00000008) == 0x00000008);
		}

		/**
		 * <code>required .common.Status status = 4;</code>
		 */
		public com.johnny.boot.openshift.domain.TypeProtos.Status getStatus() {
			return status_;
		}

		private void initFields() {
			id_ = 0L;
			name_ = "";
			parentId_ = 0L;
			status_ = com.johnny.boot.openshift.domain.TypeProtos.Status.DISABLE;
		}

		private byte memoizedIsInitialized = -1;

		public final boolean isInitialized() {
			byte isInitialized = memoizedIsInitialized;
			if (isInitialized != -1)
				return isInitialized == 1;

			if (!hasId()) {
				memoizedIsInitialized = 0;
				return false;
			}
			if (!hasName()) {
				memoizedIsInitialized = 0;
				return false;
			}
			if (!hasParentId()) {
				memoizedIsInitialized = 0;
				return false;
			}
			if (!hasStatus()) {
				memoizedIsInitialized = 0;
				return false;
			}
			memoizedIsInitialized = 1;
			return true;
		}

		public void writeTo(com.google.protobuf.CodedOutputStream output)
				throws java.io.IOException {
			getSerializedSize();
			if (((bitField0_ & 0x00000001) == 0x00000001)) {
				output.writeInt64(1, id_);
			}
			if (((bitField0_ & 0x00000002) == 0x00000002)) {
				output.writeBytes(2, getNameBytes());
			}
			if (((bitField0_ & 0x00000004) == 0x00000004)) {
				output.writeInt64(3, parentId_);
			}
			if (((bitField0_ & 0x00000008) == 0x00000008)) {
				output.writeEnum(4, status_.getNumber());
			}
			getUnknownFields().writeTo(output);
		}

		private int memoizedSerializedSize = -1;

		public int getSerializedSize() {
			int size = memoizedSerializedSize;
			if (size != -1)
				return size;

			size = 0;
			if (((bitField0_ & 0x00000001) == 0x00000001)) {
				size += com.google.protobuf.CodedOutputStream.computeInt64Size(
						1, id_);
			}
			if (((bitField0_ & 0x00000002) == 0x00000002)) {
				size += com.google.protobuf.CodedOutputStream.computeBytesSize(
						2, getNameBytes());
			}
			if (((bitField0_ & 0x00000004) == 0x00000004)) {
				size += com.google.protobuf.CodedOutputStream.computeInt64Size(
						3, parentId_);
			}
			if (((bitField0_ & 0x00000008) == 0x00000008)) {
				size += com.google.protobuf.CodedOutputStream.computeEnumSize(
						4, status_.getNumber());
			}
			size += getUnknownFields().getSerializedSize();
			memoizedSerializedSize = size;
			return size;
		}

		private static final long serialVersionUID = 0L;

		@java.lang.Override
		protected java.lang.Object writeReplace()
				throws java.io.ObjectStreamException {
			return super.writeReplace();
		}

		public static com.johnny.boot.openshift.domain.AccountProto.Account parseFrom(
				com.google.protobuf.ByteString data)
				throws com.google.protobuf.InvalidProtocolBufferException {
			return PARSER.parseFrom(data);
		}

		public static com.johnny.boot.openshift.domain.AccountProto.Account parseFrom(
				com.google.protobuf.ByteString data,
				com.google.protobuf.ExtensionRegistryLite extensionRegistry)
				throws com.google.protobuf.InvalidProtocolBufferException {
			return PARSER.parseFrom(data, extensionRegistry);
		}

		public static com.johnny.boot.openshift.domain.AccountProto.Account parseFrom(
				byte[] data)
				throws com.google.protobuf.InvalidProtocolBufferException {
			return PARSER.parseFrom(data);
		}

		public static com.johnny.boot.openshift.domain.AccountProto.Account parseFrom(
				byte[] data,
				com.google.protobuf.ExtensionRegistryLite extensionRegistry)
				throws com.google.protobuf.InvalidProtocolBufferException {
			return PARSER.parseFrom(data, extensionRegistry);
		}

		public static com.johnny.boot.openshift.domain.AccountProto.Account parseFrom(
				java.io.InputStream input) throws java.io.IOException {
			return PARSER.parseFrom(input);
		}

		public static com.johnny.boot.openshift.domain.AccountProto.Account parseFrom(
				java.io.InputStream input,
				com.google.protobuf.ExtensionRegistryLite extensionRegistry)
				throws java.io.IOException {
			return PARSER.parseFrom(input, extensionRegistry);
		}

		public static com.johnny.boot.openshift.domain.AccountProto.Account parseDelimitedFrom(
				java.io.InputStream input) throws java.io.IOException {
			return PARSER.parseDelimitedFrom(input);
		}

		public static com.johnny.boot.openshift.domain.AccountProto.Account parseDelimitedFrom(
				java.io.InputStream input,
				com.google.protobuf.ExtensionRegistryLite extensionRegistry)
				throws java.io.IOException {
			return PARSER.parseDelimitedFrom(input, extensionRegistry);
		}

		public static com.johnny.boot.openshift.domain.AccountProto.Account parseFrom(
				com.google.protobuf.CodedInputStream input)
				throws java.io.IOException {
			return PARSER.parseFrom(input);
		}

		public static com.johnny.boot.openshift.domain.AccountProto.Account parseFrom(
				com.google.protobuf.CodedInputStream input,
				com.google.protobuf.ExtensionRegistryLite extensionRegistry)
				throws java.io.IOException {
			return PARSER.parseFrom(input, extensionRegistry);
		}

		public static Builder newBuilder() {
			return Builder.create();
		}

		public Builder newBuilderForType() {
			return newBuilder();
		}

		public static Builder newBuilder(
				com.johnny.boot.openshift.domain.AccountProto.Account prototype) {
			return newBuilder().mergeFrom(prototype);
		}

		public Builder toBuilder() {
			return newBuilder(this);
		}

		@java.lang.Override
		protected Builder newBuilderForType(
				com.google.protobuf.GeneratedMessage.BuilderParent parent) {
			Builder builder = new Builder(parent);
			return builder;
		}

		/**
		 * Protobuf type {@code common.Account}
		 */
		public static final class Builder extends
				com.google.protobuf.GeneratedMessage.Builder<Builder> implements
				com.johnny.boot.openshift.domain.AccountProto.AccountOrBuilder {
			public static final com.google.protobuf.Descriptors.Descriptor getDescriptor() {
				return com.johnny.boot.openshift.domain.AccountProto.internal_static_common_Account_descriptor;
			}

			protected com.google.protobuf.GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
				return com.johnny.boot.openshift.domain.AccountProto.internal_static_common_Account_fieldAccessorTable
						.ensureFieldAccessorsInitialized(
								com.johnny.boot.openshift.domain.AccountProto.Account.class,
								com.johnny.boot.openshift.domain.AccountProto.Account.Builder.class);
			}

			// Construct using
			// com.johnny.boot.openshift.domain.AccountProto.Account.newBuilder()
			private Builder() {
				maybeForceBuilderInitialization();
			}

			private Builder(
					com.google.protobuf.GeneratedMessage.BuilderParent parent) {
				super(parent);
				maybeForceBuilderInitialization();
			}

			private void maybeForceBuilderInitialization() {
				if (com.google.protobuf.GeneratedMessage.alwaysUseFieldBuilders) {
				}
			}

			private static Builder create() {
				return new Builder();
			}

			public Builder clear() {
				super.clear();
				id_ = 0L;
				bitField0_ = (bitField0_ & ~0x00000001);
				name_ = "";
				bitField0_ = (bitField0_ & ~0x00000002);
				parentId_ = 0L;
				bitField0_ = (bitField0_ & ~0x00000004);
				status_ = com.johnny.boot.openshift.domain.TypeProtos.Status.DISABLE;
				bitField0_ = (bitField0_ & ~0x00000008);
				return this;
			}

			public Builder clone() {
				return create().mergeFrom(buildPartial());
			}

			public com.google.protobuf.Descriptors.Descriptor getDescriptorForType() {
				return com.johnny.boot.openshift.domain.AccountProto.internal_static_common_Account_descriptor;
			}

			public com.johnny.boot.openshift.domain.AccountProto.Account getDefaultInstanceForType() {
				return com.johnny.boot.openshift.domain.AccountProto.Account
						.getDefaultInstance();
			}

			public com.johnny.boot.openshift.domain.AccountProto.Account build() {
				com.johnny.boot.openshift.domain.AccountProto.Account result = buildPartial();
				if (!result.isInitialized()) {
					throw newUninitializedMessageException(result);
				}
				return result;
			}

			public com.johnny.boot.openshift.domain.AccountProto.Account buildPartial() {
				com.johnny.boot.openshift.domain.AccountProto.Account result = new com.johnny.boot.openshift.domain.AccountProto.Account(
						this);
				int from_bitField0_ = bitField0_;
				int to_bitField0_ = 0;
				if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
					to_bitField0_ |= 0x00000001;
				}
				result.id_ = id_;
				if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
					to_bitField0_ |= 0x00000002;
				}
				result.name_ = name_;
				if (((from_bitField0_ & 0x00000004) == 0x00000004)) {
					to_bitField0_ |= 0x00000004;
				}
				result.parentId_ = parentId_;
				if (((from_bitField0_ & 0x00000008) == 0x00000008)) {
					to_bitField0_ |= 0x00000008;
				}
				result.status_ = status_;
				result.bitField0_ = to_bitField0_;
				onBuilt();
				return result;
			}

			public Builder mergeFrom(com.google.protobuf.Message other) {
				if (other instanceof com.johnny.boot.openshift.domain.AccountProto.Account) {
					return mergeFrom((com.johnny.boot.openshift.domain.AccountProto.Account) other);
				} else {
					super.mergeFrom(other);
					return this;
				}
			}

			public Builder mergeFrom(
					com.johnny.boot.openshift.domain.AccountProto.Account other) {
				if (other == com.johnny.boot.openshift.domain.AccountProto.Account
						.getDefaultInstance())
					return this;
				if (other.hasId()) {
					setId(other.getId());
				}
				if (other.hasName()) {
					bitField0_ |= 0x00000002;
					name_ = other.name_;
					onChanged();
				}
				if (other.hasParentId()) {
					setParentId(other.getParentId());
				}
				if (other.hasStatus()) {
					setStatus(other.getStatus());
				}
				this.mergeUnknownFields(other.getUnknownFields());
				return this;
			}

			public final boolean isInitialized() {
				if (!hasId()) {

					return false;
				}
				if (!hasName()) {

					return false;
				}
				if (!hasParentId()) {

					return false;
				}
				if (!hasStatus()) {

					return false;
				}
				return true;
			}

			public Builder mergeFrom(
					com.google.protobuf.CodedInputStream input,
					com.google.protobuf.ExtensionRegistryLite extensionRegistry)
					throws java.io.IOException {
				com.johnny.boot.openshift.domain.AccountProto.Account parsedMessage = null;
				try {
					parsedMessage = PARSER.parsePartialFrom(input,
							extensionRegistry);
				} catch (com.google.protobuf.InvalidProtocolBufferException e) {
					parsedMessage = (com.johnny.boot.openshift.domain.AccountProto.Account) e
							.getUnfinishedMessage();
					throw e;
				} finally {
					if (parsedMessage != null) {
						mergeFrom(parsedMessage);
					}
				}
				return this;
			}

			private int bitField0_;

			// required int64 id = 1;
			private long id_;

			/**
			 * <code>required int64 id = 1;</code>
			 */
			public boolean hasId() {
				return ((bitField0_ & 0x00000001) == 0x00000001);
			}

			/**
			 * <code>required int64 id = 1;</code>
			 */
			public long getId() {
				return id_;
			}

			/**
			 * <code>required int64 id = 1;</code>
			 */
			public Builder setId(long value) {
				bitField0_ |= 0x00000001;
				id_ = value;
				onChanged();
				return this;
			}

			/**
			 * <code>required int64 id = 1;</code>
			 */
			public Builder clearId() {
				bitField0_ = (bitField0_ & ~0x00000001);
				id_ = 0L;
				onChanged();
				return this;
			}

			// required string name = 2;
			private java.lang.Object name_ = "";

			/**
			 * <code>required string name = 2;</code>
			 */
			public boolean hasName() {
				return ((bitField0_ & 0x00000002) == 0x00000002);
			}

			/**
			 * <code>required string name = 2;</code>
			 */
			public java.lang.String getName() {
				java.lang.Object ref = name_;
				if (!(ref instanceof java.lang.String)) {
					java.lang.String s = ((com.google.protobuf.ByteString) ref)
							.toStringUtf8();
					name_ = s;
					return s;
				} else {
					return (java.lang.String) ref;
				}
			}

			/**
			 * <code>required string name = 2;</code>
			 */
			public com.google.protobuf.ByteString getNameBytes() {
				java.lang.Object ref = name_;
				if (ref instanceof String) {
					com.google.protobuf.ByteString b = com.google.protobuf.ByteString
							.copyFromUtf8((java.lang.String) ref);
					name_ = b;
					return b;
				} else {
					return (com.google.protobuf.ByteString) ref;
				}
			}

			/**
			 * <code>required string name = 2;</code>
			 */
			public Builder setName(java.lang.String value) {
				if (value == null) {
					throw new NullPointerException();
				}
				bitField0_ |= 0x00000002;
				name_ = value;
				onChanged();
				return this;
			}

			/**
			 * <code>required string name = 2;</code>
			 */
			public Builder clearName() {
				bitField0_ = (bitField0_ & ~0x00000002);
				name_ = getDefaultInstance().getName();
				onChanged();
				return this;
			}

			/**
			 * <code>required string name = 2;</code>
			 */
			public Builder setNameBytes(com.google.protobuf.ByteString value) {
				if (value == null) {
					throw new NullPointerException();
				}
				bitField0_ |= 0x00000002;
				name_ = value;
				onChanged();
				return this;
			}

			// required int64 parentId = 3;
			private long parentId_;

			/**
			 * <code>required int64 parentId = 3;</code>
			 */
			public boolean hasParentId() {
				return ((bitField0_ & 0x00000004) == 0x00000004);
			}

			/**
			 * <code>required int64 parentId = 3;</code>
			 */
			public long getParentId() {
				return parentId_;
			}

			/**
			 * <code>required int64 parentId = 3;</code>
			 */
			public Builder setParentId(long value) {
				bitField0_ |= 0x00000004;
				parentId_ = value;
				onChanged();
				return this;
			}

			/**
			 * <code>required int64 parentId = 3;</code>
			 */
			public Builder clearParentId() {
				bitField0_ = (bitField0_ & ~0x00000004);
				parentId_ = 0L;
				onChanged();
				return this;
			}

			// required .common.Status status = 4;
			private com.johnny.boot.openshift.domain.TypeProtos.Status status_ = com.johnny.boot.openshift.domain.TypeProtos.Status.DISABLE;

			/**
			 * <code>required .common.Status status = 4;</code>
			 */
			public boolean hasStatus() {
				return ((bitField0_ & 0x00000008) == 0x00000008);
			}

			/**
			 * <code>required .common.Status status = 4;</code>
			 */
			public com.johnny.boot.openshift.domain.TypeProtos.Status getStatus() {
				return status_;
			}

			/**
			 * <code>required .common.Status status = 4;</code>
			 */
			public Builder setStatus(
					com.johnny.boot.openshift.domain.TypeProtos.Status value) {
				if (value == null) {
					throw new NullPointerException();
				}
				bitField0_ |= 0x00000008;
				status_ = value;
				onChanged();
				return this;
			}

			/**
			 * <code>required .common.Status status = 4;</code>
			 */
			public Builder clearStatus() {
				bitField0_ = (bitField0_ & ~0x00000008);
				status_ = com.johnny.boot.openshift.domain.TypeProtos.Status.DISABLE;
				onChanged();
				return this;
			}

			// @@protoc_insertion_point(builder_scope:common.Account)
		}

		static {
			defaultInstance = new Account(true);
			defaultInstance.initFields();
		}

		// @@protoc_insertion_point(class_scope:common.Account)
	}

	private static com.google.protobuf.Descriptors.Descriptor internal_static_common_Account_descriptor;
	private static com.google.protobuf.GeneratedMessage.FieldAccessorTable internal_static_common_Account_fieldAccessorTable;

	public static com.google.protobuf.Descriptors.FileDescriptor getDescriptor() {
		return descriptor;
	}

	private static com.google.protobuf.Descriptors.FileDescriptor descriptor;
	static {
		java.lang.String[] descriptorData = { "\n\rAccount.proto\022\006common\032\013Types.proto\"U\n\007"
				+ "Account\022\n\n\002id\030\001 \002(\003\022\014\n\004name\030\002 \002(\t\022\020\n\010par"
				+ "entId\030\003 \002(\003\022\036\n\006status\030\004 \002(\0162\016.common.Sta"
				+ "tusB0\n com.johnny.boot.openshift.domainB"
				+ "\014AccountProto" };
		com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner = new com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner() {
			public com.google.protobuf.ExtensionRegistry assignDescriptors(
					com.google.protobuf.Descriptors.FileDescriptor root) {
				descriptor = root;
				internal_static_common_Account_descriptor = getDescriptor()
						.getMessageTypes().get(0);
				internal_static_common_Account_fieldAccessorTable = new com.google.protobuf.GeneratedMessage.FieldAccessorTable(
						internal_static_common_Account_descriptor,
						new java.lang.String[] { "Id", "Name", "ParentId",
								"Status", });
				return null;
			}
		};
		com.google.protobuf.Descriptors.FileDescriptor
				.internalBuildGeneratedFileFrom(
						descriptorData,
						new com.google.protobuf.Descriptors.FileDescriptor[] { com.johnny.boot.openshift.domain.TypeProtos
								.getDescriptor(), }, assigner);
	}

	// @@protoc_insertion_point(outer_class_scope)
}