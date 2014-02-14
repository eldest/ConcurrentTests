package com.eldest.spring;

class HelloWorldImpl implements HelloWorld
{
  private String message;
  public void setMessage(String theMessage)
  {
    message = theMessage;
  }
  public void sayMessage()
  {
    System.out.println("Hello world! Hello " + message + "!");
  }
  
}

