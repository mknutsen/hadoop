/**
 * Autogenerated by Avro
 * 
 * DO NOT EDIT DIRECTLY
 */
package twitter.avro;  
@SuppressWarnings("all")
@org.apache.avro.specific.AvroGenerated
public class User extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = -2914734527276386904L;
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"User\",\"namespace\":\"twitter.avro\",\"fields\":[{\"name\":\"id\",\"type\":\"long\"},{\"name\":\"screen_name\",\"type\":\"string\"},{\"name\":\"location\",\"type\":[\"string\",\"null\"],\"default\":\"null\"},{\"name\":\"description\",\"type\":[\"string\",\"null\"],\"default\":\"null\"},{\"name\":\"followers_count\",\"type\":\"int\"},{\"name\":\"statuses_count\",\"type\":\"int\"},{\"name\":\"geo_enabled\",\"type\":\"boolean\"},{\"name\":\"lang\",\"type\":\"string\"}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }
  @Deprecated public long id;
  @Deprecated public java.lang.CharSequence screen_name;
  @Deprecated public java.lang.CharSequence location;
  @Deprecated public java.lang.CharSequence description;
  @Deprecated public int followers_count;
  @Deprecated public int statuses_count;
  @Deprecated public boolean geo_enabled;
  @Deprecated public java.lang.CharSequence lang;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>. 
   */
  public User() {}

  /**
   * All-args constructor.
   */
  public User(java.lang.Long id, java.lang.CharSequence screen_name, java.lang.CharSequence location, java.lang.CharSequence description, java.lang.Integer followers_count, java.lang.Integer statuses_count, java.lang.Boolean geo_enabled, java.lang.CharSequence lang) {
    this.id = id;
    this.screen_name = screen_name;
    this.location = location;
    this.description = description;
    this.followers_count = followers_count;
    this.statuses_count = statuses_count;
    this.geo_enabled = geo_enabled;
    this.lang = lang;
  }

  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call. 
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return id;
    case 1: return screen_name;
    case 2: return location;
    case 3: return description;
    case 4: return followers_count;
    case 5: return statuses_count;
    case 6: return geo_enabled;
    case 7: return lang;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }
  // Used by DatumReader.  Applications should not call. 
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: id = (java.lang.Long)value$; break;
    case 1: screen_name = (java.lang.CharSequence)value$; break;
    case 2: location = (java.lang.CharSequence)value$; break;
    case 3: description = (java.lang.CharSequence)value$; break;
    case 4: followers_count = (java.lang.Integer)value$; break;
    case 5: statuses_count = (java.lang.Integer)value$; break;
    case 6: geo_enabled = (java.lang.Boolean)value$; break;
    case 7: lang = (java.lang.CharSequence)value$; break;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  /**
   * Gets the value of the 'id' field.
   */
  public java.lang.Long getId() {
    return id;
  }

  /**
   * Sets the value of the 'id' field.
   * @param value the value to set.
   */
  public void setId(java.lang.Long value) {
    this.id = value;
  }

  /**
   * Gets the value of the 'screen_name' field.
   */
  public java.lang.CharSequence getScreenName() {
    return screen_name;
  }

  /**
   * Sets the value of the 'screen_name' field.
   * @param value the value to set.
   */
  public void setScreenName(java.lang.CharSequence value) {
    this.screen_name = value;
  }

  /**
   * Gets the value of the 'location' field.
   */
  public java.lang.CharSequence getLocation() {
    return location;
  }

  /**
   * Sets the value of the 'location' field.
   * @param value the value to set.
   */
  public void setLocation(java.lang.CharSequence value) {
    this.location = value;
  }

  /**
   * Gets the value of the 'description' field.
   */
  public java.lang.CharSequence getDescription() {
    return description;
  }

  /**
   * Sets the value of the 'description' field.
   * @param value the value to set.
   */
  public void setDescription(java.lang.CharSequence value) {
    this.description = value;
  }

  /**
   * Gets the value of the 'followers_count' field.
   */
  public java.lang.Integer getFollowersCount() {
    return followers_count;
  }

  /**
   * Sets the value of the 'followers_count' field.
   * @param value the value to set.
   */
  public void setFollowersCount(java.lang.Integer value) {
    this.followers_count = value;
  }

  /**
   * Gets the value of the 'statuses_count' field.
   */
  public java.lang.Integer getStatusesCount() {
    return statuses_count;
  }

  /**
   * Sets the value of the 'statuses_count' field.
   * @param value the value to set.
   */
  public void setStatusesCount(java.lang.Integer value) {
    this.statuses_count = value;
  }

  /**
   * Gets the value of the 'geo_enabled' field.
   */
  public java.lang.Boolean getGeoEnabled() {
    return geo_enabled;
  }

  /**
   * Sets the value of the 'geo_enabled' field.
   * @param value the value to set.
   */
  public void setGeoEnabled(java.lang.Boolean value) {
    this.geo_enabled = value;
  }

  /**
   * Gets the value of the 'lang' field.
   */
  public java.lang.CharSequence getLang() {
    return lang;
  }

  /**
   * Sets the value of the 'lang' field.
   * @param value the value to set.
   */
  public void setLang(java.lang.CharSequence value) {
    this.lang = value;
  }

  /**
   * Creates a new User RecordBuilder.
   * @return A new User RecordBuilder
   */
  public static twitter.avro.User.Builder newBuilder() {
    return new twitter.avro.User.Builder();
  }
  
  /**
   * Creates a new User RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new User RecordBuilder
   */
  public static twitter.avro.User.Builder newBuilder(twitter.avro.User.Builder other) {
    return new twitter.avro.User.Builder(other);
  }
  
  /**
   * Creates a new User RecordBuilder by copying an existing User instance.
   * @param other The existing instance to copy.
   * @return A new User RecordBuilder
   */
  public static twitter.avro.User.Builder newBuilder(twitter.avro.User other) {
    return new twitter.avro.User.Builder(other);
  }
  
  /**
   * RecordBuilder for User instances.
   */
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<User>
    implements org.apache.avro.data.RecordBuilder<User> {

    private long id;
    private java.lang.CharSequence screen_name;
    private java.lang.CharSequence location;
    private java.lang.CharSequence description;
    private int followers_count;
    private int statuses_count;
    private boolean geo_enabled;
    private java.lang.CharSequence lang;

    /** Creates a new Builder */
    private Builder() {
      super(twitter.avro.User.SCHEMA$);
    }
    
    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(twitter.avro.User.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.id)) {
        this.id = data().deepCopy(fields()[0].schema(), other.id);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.screen_name)) {
        this.screen_name = data().deepCopy(fields()[1].schema(), other.screen_name);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.location)) {
        this.location = data().deepCopy(fields()[2].schema(), other.location);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.description)) {
        this.description = data().deepCopy(fields()[3].schema(), other.description);
        fieldSetFlags()[3] = true;
      }
      if (isValidValue(fields()[4], other.followers_count)) {
        this.followers_count = data().deepCopy(fields()[4].schema(), other.followers_count);
        fieldSetFlags()[4] = true;
      }
      if (isValidValue(fields()[5], other.statuses_count)) {
        this.statuses_count = data().deepCopy(fields()[5].schema(), other.statuses_count);
        fieldSetFlags()[5] = true;
      }
      if (isValidValue(fields()[6], other.geo_enabled)) {
        this.geo_enabled = data().deepCopy(fields()[6].schema(), other.geo_enabled);
        fieldSetFlags()[6] = true;
      }
      if (isValidValue(fields()[7], other.lang)) {
        this.lang = data().deepCopy(fields()[7].schema(), other.lang);
        fieldSetFlags()[7] = true;
      }
    }
    
    /**
     * Creates a Builder by copying an existing User instance
     * @param other The existing instance to copy.
     */
    private Builder(twitter.avro.User other) {
            super(twitter.avro.User.SCHEMA$);
      if (isValidValue(fields()[0], other.id)) {
        this.id = data().deepCopy(fields()[0].schema(), other.id);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.screen_name)) {
        this.screen_name = data().deepCopy(fields()[1].schema(), other.screen_name);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.location)) {
        this.location = data().deepCopy(fields()[2].schema(), other.location);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.description)) {
        this.description = data().deepCopy(fields()[3].schema(), other.description);
        fieldSetFlags()[3] = true;
      }
      if (isValidValue(fields()[4], other.followers_count)) {
        this.followers_count = data().deepCopy(fields()[4].schema(), other.followers_count);
        fieldSetFlags()[4] = true;
      }
      if (isValidValue(fields()[5], other.statuses_count)) {
        this.statuses_count = data().deepCopy(fields()[5].schema(), other.statuses_count);
        fieldSetFlags()[5] = true;
      }
      if (isValidValue(fields()[6], other.geo_enabled)) {
        this.geo_enabled = data().deepCopy(fields()[6].schema(), other.geo_enabled);
        fieldSetFlags()[6] = true;
      }
      if (isValidValue(fields()[7], other.lang)) {
        this.lang = data().deepCopy(fields()[7].schema(), other.lang);
        fieldSetFlags()[7] = true;
      }
    }

    /**
      * Gets the value of the 'id' field.
      * @return The value.
      */
    public java.lang.Long getId() {
      return id;
    }

    /**
      * Sets the value of the 'id' field.
      * @param value The value of 'id'.
      * @return This builder.
      */
    public twitter.avro.User.Builder setId(long value) {
      validate(fields()[0], value);
      this.id = value;
      fieldSetFlags()[0] = true;
      return this; 
    }

    /**
      * Checks whether the 'id' field has been set.
      * @return True if the 'id' field has been set, false otherwise.
      */
    public boolean hasId() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'id' field.
      * @return This builder.
      */
    public twitter.avro.User.Builder clearId() {
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
      * Gets the value of the 'screen_name' field.
      * @return The value.
      */
    public java.lang.CharSequence getScreenName() {
      return screen_name;
    }

    /**
      * Sets the value of the 'screen_name' field.
      * @param value The value of 'screen_name'.
      * @return This builder.
      */
    public twitter.avro.User.Builder setScreenName(java.lang.CharSequence value) {
      validate(fields()[1], value);
      this.screen_name = value;
      fieldSetFlags()[1] = true;
      return this; 
    }

    /**
      * Checks whether the 'screen_name' field has been set.
      * @return True if the 'screen_name' field has been set, false otherwise.
      */
    public boolean hasScreenName() {
      return fieldSetFlags()[1];
    }


    /**
      * Clears the value of the 'screen_name' field.
      * @return This builder.
      */
    public twitter.avro.User.Builder clearScreenName() {
      screen_name = null;
      fieldSetFlags()[1] = false;
      return this;
    }

    /**
      * Gets the value of the 'location' field.
      * @return The value.
      */
    public java.lang.CharSequence getLocation() {
      return location;
    }

    /**
      * Sets the value of the 'location' field.
      * @param value The value of 'location'.
      * @return This builder.
      */
    public twitter.avro.User.Builder setLocation(java.lang.CharSequence value) {
      validate(fields()[2], value);
      this.location = value;
      fieldSetFlags()[2] = true;
      return this; 
    }

    /**
      * Checks whether the 'location' field has been set.
      * @return True if the 'location' field has been set, false otherwise.
      */
    public boolean hasLocation() {
      return fieldSetFlags()[2];
    }


    /**
      * Clears the value of the 'location' field.
      * @return This builder.
      */
    public twitter.avro.User.Builder clearLocation() {
      location = null;
      fieldSetFlags()[2] = false;
      return this;
    }

    /**
      * Gets the value of the 'description' field.
      * @return The value.
      */
    public java.lang.CharSequence getDescription() {
      return description;
    }

    /**
      * Sets the value of the 'description' field.
      * @param value The value of 'description'.
      * @return This builder.
      */
    public twitter.avro.User.Builder setDescription(java.lang.CharSequence value) {
      validate(fields()[3], value);
      this.description = value;
      fieldSetFlags()[3] = true;
      return this; 
    }

    /**
      * Checks whether the 'description' field has been set.
      * @return True if the 'description' field has been set, false otherwise.
      */
    public boolean hasDescription() {
      return fieldSetFlags()[3];
    }


    /**
      * Clears the value of the 'description' field.
      * @return This builder.
      */
    public twitter.avro.User.Builder clearDescription() {
      description = null;
      fieldSetFlags()[3] = false;
      return this;
    }

    /**
      * Gets the value of the 'followers_count' field.
      * @return The value.
      */
    public java.lang.Integer getFollowersCount() {
      return followers_count;
    }

    /**
      * Sets the value of the 'followers_count' field.
      * @param value The value of 'followers_count'.
      * @return This builder.
      */
    public twitter.avro.User.Builder setFollowersCount(int value) {
      validate(fields()[4], value);
      this.followers_count = value;
      fieldSetFlags()[4] = true;
      return this; 
    }

    /**
      * Checks whether the 'followers_count' field has been set.
      * @return True if the 'followers_count' field has been set, false otherwise.
      */
    public boolean hasFollowersCount() {
      return fieldSetFlags()[4];
    }


    /**
      * Clears the value of the 'followers_count' field.
      * @return This builder.
      */
    public twitter.avro.User.Builder clearFollowersCount() {
      fieldSetFlags()[4] = false;
      return this;
    }

    /**
      * Gets the value of the 'statuses_count' field.
      * @return The value.
      */
    public java.lang.Integer getStatusesCount() {
      return statuses_count;
    }

    /**
      * Sets the value of the 'statuses_count' field.
      * @param value The value of 'statuses_count'.
      * @return This builder.
      */
    public twitter.avro.User.Builder setStatusesCount(int value) {
      validate(fields()[5], value);
      this.statuses_count = value;
      fieldSetFlags()[5] = true;
      return this; 
    }

    /**
      * Checks whether the 'statuses_count' field has been set.
      * @return True if the 'statuses_count' field has been set, false otherwise.
      */
    public boolean hasStatusesCount() {
      return fieldSetFlags()[5];
    }


    /**
      * Clears the value of the 'statuses_count' field.
      * @return This builder.
      */
    public twitter.avro.User.Builder clearStatusesCount() {
      fieldSetFlags()[5] = false;
      return this;
    }

    /**
      * Gets the value of the 'geo_enabled' field.
      * @return The value.
      */
    public java.lang.Boolean getGeoEnabled() {
      return geo_enabled;
    }

    /**
      * Sets the value of the 'geo_enabled' field.
      * @param value The value of 'geo_enabled'.
      * @return This builder.
      */
    public twitter.avro.User.Builder setGeoEnabled(boolean value) {
      validate(fields()[6], value);
      this.geo_enabled = value;
      fieldSetFlags()[6] = true;
      return this; 
    }

    /**
      * Checks whether the 'geo_enabled' field has been set.
      * @return True if the 'geo_enabled' field has been set, false otherwise.
      */
    public boolean hasGeoEnabled() {
      return fieldSetFlags()[6];
    }


    /**
      * Clears the value of the 'geo_enabled' field.
      * @return This builder.
      */
    public twitter.avro.User.Builder clearGeoEnabled() {
      fieldSetFlags()[6] = false;
      return this;
    }

    /**
      * Gets the value of the 'lang' field.
      * @return The value.
      */
    public java.lang.CharSequence getLang() {
      return lang;
    }

    /**
      * Sets the value of the 'lang' field.
      * @param value The value of 'lang'.
      * @return This builder.
      */
    public twitter.avro.User.Builder setLang(java.lang.CharSequence value) {
      validate(fields()[7], value);
      this.lang = value;
      fieldSetFlags()[7] = true;
      return this; 
    }

    /**
      * Checks whether the 'lang' field has been set.
      * @return True if the 'lang' field has been set, false otherwise.
      */
    public boolean hasLang() {
      return fieldSetFlags()[7];
    }


    /**
      * Clears the value of the 'lang' field.
      * @return This builder.
      */
    public twitter.avro.User.Builder clearLang() {
      lang = null;
      fieldSetFlags()[7] = false;
      return this;
    }

    @Override
    public User build() {
      try {
        User record = new User();
        record.id = fieldSetFlags()[0] ? this.id : (java.lang.Long) defaultValue(fields()[0]);
        record.screen_name = fieldSetFlags()[1] ? this.screen_name : (java.lang.CharSequence) defaultValue(fields()[1]);
        record.location = fieldSetFlags()[2] ? this.location : (java.lang.CharSequence) defaultValue(fields()[2]);
        record.description = fieldSetFlags()[3] ? this.description : (java.lang.CharSequence) defaultValue(fields()[3]);
        record.followers_count = fieldSetFlags()[4] ? this.followers_count : (java.lang.Integer) defaultValue(fields()[4]);
        record.statuses_count = fieldSetFlags()[5] ? this.statuses_count : (java.lang.Integer) defaultValue(fields()[5]);
        record.geo_enabled = fieldSetFlags()[6] ? this.geo_enabled : (java.lang.Boolean) defaultValue(fields()[6]);
        record.lang = fieldSetFlags()[7] ? this.lang : (java.lang.CharSequence) defaultValue(fields()[7]);
        return record;
      } catch (Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  private static final org.apache.avro.io.DatumWriter
    WRITER$ = new org.apache.avro.specific.SpecificDatumWriter(SCHEMA$);  

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, org.apache.avro.specific.SpecificData.getEncoder(out));
  }

  private static final org.apache.avro.io.DatumReader
    READER$ = new org.apache.avro.specific.SpecificDatumReader(SCHEMA$);  

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, org.apache.avro.specific.SpecificData.getDecoder(in));
  }

}