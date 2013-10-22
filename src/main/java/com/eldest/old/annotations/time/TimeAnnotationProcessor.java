/*
 * Copyright (C) 2008-2009 Eremenko EremenkoAA@ics-ware.com
 * 
 * Information Corporative Systems, ICS LLC
 * TelaDictum LLC
 *
 * TimeAnnotationProcessor
 * 
 * Created: 03.05.2010
 * History: 
 *    
 *
 * $Id$
 * 
 */
package com.eldest.old.annotations.time;

import com.sun.tools.javac.code.Flags;
import com.sun.tools.javac.code.TypeTags;
import com.sun.tools.javac.model.JavacElements;
import com.sun.tools.javac.processing.JavacProcessingEnvironment;
import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.tree.JCTree.*;
import com.sun.tools.javac.tree.TreeMaker;
import com.sun.tools.javac.util.List;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import java.util.Set;

/**
 * @author EremenkoAA
 *
 */
@SupportedAnnotationTypes(value = { TimeAnnotationProcessor.ANNOTATION_TYPE })
@SupportedSourceVersion(SourceVersion.RELEASE_6)
public class TimeAnnotationProcessor extends AbstractProcessor {

	public static final String ANNOTATION_TYPE = "annotations.time.Time";
	private JavacProcessingEnvironment javacProcessingEnv;
	private TreeMaker maker;

	@Override
	public void init(ProcessingEnvironment procEnv) {
		super.init(procEnv);
		this.javacProcessingEnv = (JavacProcessingEnvironment) procEnv;
		this.maker = TreeMaker.instance(javacProcessingEnv.getContext());
	}

	@Override
	public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
		if (annotations == null || annotations.isEmpty()) {
			return false;
		}

		final Elements elements = javacProcessingEnv.getElementUtils();

		final TypeElement annotation = elements.getTypeElement(ANNOTATION_TYPE);

		if (annotation != null) {
			// Выбираем все элементы, у которых стоит наша аннотация
			final Set<? extends Element> methods = roundEnv.getElementsAnnotatedWith(annotation);

			JavacElements utils = javacProcessingEnv.getElementUtils();
			for (final Element m : methods) {
				Time time = m.getAnnotation(Time.class);
				if (time != null) {
					JCTree blockNode = utils.getTree(m);
					// Нам нужны только описания методов
					if (blockNode instanceof JCMethodDecl) {
						// Получаем содержимое метода
						final List<JCStatement> statements = ((JCMethodDecl) blockNode).body.stats;

						// Новое тело метода
						List<JCStatement> newStatements = List.nil();
						// Добавляем в начало метода сохранение текущего времени
						JCVariableDecl var = makeTimeStartVar(maker, utils, time);
						newStatements = newStatements.append(var);

						// Создаём тело блока try, копируем в него оригинальное содержимое метода
						List<JCStatement> tryBlock = List.nil();
						for (JCStatement statement : statements) {
							tryBlock = tryBlock.append(statement);
						}

						// Создаём тело блока finally, добавляем в него вывод затраченного времени
						JCBlock finalizer = makePrintBlock(maker, utils, time, var);
						JCStatement stat = maker.Try(maker.Block(0, tryBlock), List.<JCCatch> nil(), finalizer);
						newStatements = newStatements.append(stat);

						// Заменяем старый код метода на новый
						((JCMethodDecl) blockNode).body.stats = newStatements;
					}
				}
			}

			return true;
		}

		return false;
	}

	private JCExpression makeCurrentTime(TreeMaker maker, JavacElements utils, Time time) {
		// Создаём вызов System.nanoTime или System.currentTimeMillis
		JCExpression exp = maker.Ident(utils.getName("System"));
		String methodName;
		switch (time.interval()) {
		case NANOSECOND:
			methodName = "nanoTime";
			break;
		default:
			methodName = "currentTimeMillis";
			break;
		}
		exp = maker.Select(exp, utils.getName(methodName));
		return maker.Apply(List.<JCExpression> nil(), exp, List.<JCExpression> nil());
	}

	protected JCVariableDecl makeTimeStartVar(TreeMaker maker, JavacElements utils, Time time) {
		// Создаём финальную переменную для хранения времени старта. Имя переменной в виде time_start_{random}
		JCExpression currentTime = makeCurrentTime(maker, utils, time);
		String fieldName = fieldName = "time_start_" + (int) (Math.random() * 10000);
		return maker.VarDef(maker.Modifiers(Flags.FINAL), utils.getName(fieldName), maker.TypeIdent(TypeTags.LONG), currentTime);
	}

	protected JCBlock makePrintBlock(TreeMaker maker, JavacElements utils, Time time, JCVariableDecl var) {
		// Создаём вызов System.out.println
		JCExpression printlnExpression = maker.Ident(utils.getName("System"));
		printlnExpression = maker.Select(printlnExpression, utils.getName("out"));
		printlnExpression = maker.Select(printlnExpression, utils.getName("println"));

		// Создаём блок вычисления затраченного времени (currentTime - startTime)
		JCExpression currentTime = makeCurrentTime(maker, utils, time);
		JCExpression elapsedTime = maker.Binary(JCTree.MINUS, currentTime, maker.Ident(var.name));

		// Форматируем результат
		JCExpression formatExpression = maker.Ident(utils.getName("String"));
		formatExpression = maker.Select(formatExpression, utils.getName("format"));

		// Собираем все кусочки вместе
		List<JCExpression> formatArgs = List.nil();
		formatArgs.append(maker.Literal(time.format()));
		formatArgs.append(elapsedTime);

		JCExpression format = maker.Apply(List.<JCExpression> nil(), formatExpression, formatArgs);

		List<JCExpression> printlnArgs = List.nil();
		printlnArgs.append(format);

		JCExpression print = maker.Apply(List.<JCExpression> nil(), printlnExpression, printlnArgs);
		JCExpressionStatement stmt = maker.Exec(print);

		List<JCStatement> stmts = List.nil();
		stmts.append(stmt);

		return maker.Block(0, stmts);
	}
}