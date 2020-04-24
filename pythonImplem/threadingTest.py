import time
import threading
import os

def wait1():
    print("Task 1 assigned to thread: {}".format(threading.current_thread().name)) 
    print("ID of process running task 1: {}".format(os.getpid())) 
    time.sleep(5)

def wait2():
    print("Task 2 assigned to thread: {}".format(threading.current_thread().name)) 
    print("ID of process running task 2: {}".format(os.getpid())) 
    time.sleep(5)
    

 # print ID of current process 
print("ID of process running main program: {}".format(os.getpid())) 
  
    # print name of main thread 
print("Main thread name: {}".format(threading.current_thread().name)) 
  
t1 = threading.Thread(target=wait1, name='t1') 
t2 = threading.Thread(target=wait2, name='t2')

t1.start()
t2.start()
