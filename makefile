
JFLAGS = -g
JC = javac
JVM= java 

.SUFFIXES: .java .class

.java.class:
	$(JC) $(JFLAGS) $*.java

#Question.class : Question.java Answer.class
CLASSES = \
	Answer.java \
	MCAnswer.java \
	MCSAAnswer.java \
	MCMAAnswer.java \
	SAAnswer.java \
	NumAnswer.java \
	Question.java \
	MCQuestion.java \
	MCSAQuestion.java \
	MCMAQuestion.java \
	NumQuestion.java \
	SAQuestion.java \
	Exam.java \
	ScannerFactory.java \
	ExamTester.java 

MAIN = ExamTester 

default: classes

classes: $(CLASSES:.java=.class)

run: $(MAIN)
	$(JVM) $(MAIN)

clean:
	$(RM) *.class