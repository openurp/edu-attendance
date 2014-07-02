package org.openurp.webapp.apps.teach.attendance

import org.beangle.commons.inject.bind.AbstractBindModule
import org.beangle.commons.io.ClasspathResourceLoader
import org.beangle.commons.web.resource.ResourceProcessor
import org.beangle.commons.web.resource.filter.{ ContentTypeFilter, HeaderFilter }
import org.beangle.commons.web.resource.impl.PathResolverImpl
import org.openurp.webapp.apps.teach.attendance.service.EhcacheManager
import org.openurp.webapp.apps.teach.attendance.service.impl.AttendanceServiceImpl
import org.springframework.transaction.interceptor.TransactionProxyFactoryBean
import org.beangle.data.jpa.hibernate.HibernateEntityDao
import org.beangle.spring.hibernate.HibernateTransactionManager
import org.beangle.commons.jndi.JndiDataSourceFactory
import org.beangle.spring.hibernate.LocalSessionFactoryBean
import org.springframework.beans.factory.config.PropertiesFactoryBean

class AttendanceModule extends AbstractBindModule {

  def binding(): Unit = {
    bind(classOf[PathResolverImpl])
    bind(classOf[ClasspathResourceLoader]).constructor("static")

    bind(classOf[ResourceProcessor]).property("filters", list(classOf[ContentTypeFilter], classOf[HeaderFilter]))

    bind(classOf[EhcacheManager])

    bind("eamsDataSource", classOf[JndiDataSourceFactory]).constructor("jdbc/eams")
    bind("attendanceHibernateConfig", classOf[PropertiesFactoryBean]).property(
      "properties",
      props(
        // "hibernate.temp.use_jdbc_metadata_defaults=false",
        "hibernate.dialect=org.hibernate.dialect.Oracle10gDialect",
        // "hibernate.hbm2ddl.auto=validate",
        "hibernate.max_fetch_depth=1", "hibernate.default_batch_fetch_size=500",
        "hibernate.jdbc.fetch_size=8", "hibernate.jdbc.batch_size=20",
        "hibernate.jdbc.batch_versioned_data=true", "hibernate.jdbc.use_streams_for_binary=true",
        "hibernate.jdbc.use_get_generated_keys=true",
        "hibernate.cache.region.factory_class=org.hibernate.cache.EhCacheRegionFactory",
        "hibernate.cache.use_second_level_cache=true", "hibernate.cache.use_query_cache=true",
        "hibernate.query.substitutions=true 1, false 0, yes 'Y', no 'N'", "hibernate.show_sql=false"))

    bind("attendanceSessionFactory", classOf[LocalSessionFactoryBean])
      .property("configurationClass", "org.beangle.data.jpa.hibernate.OverrideConfiguration")
      .property("hibernateProperties", ref("attendanceHibernateConfig"))
      .property("configLocations", "classpath*:META-INF/hibernate1.cfg.xml")
      .property("persistLocations", "classpath*:META-INF/beangle/persist1.properties")
      .constructor(ref("eamsDataSource"))

    bind("attendanceTransactionManager", classOf[HibernateTransactionManager]).constructor(ref("attendanceSessionFactory"))

    //    bind("attendanceBaseTransactionProxy", classOf[TransactionProxyFactoryBean]).setAbstract().property(
    //      "transactionAttributes",
    //      props("save*=PROPAGATION_REQUIRED", "update*=PROPAGATION_REQUIRED", "delete*=PROPAGATION_REQUIRED",
    //        "batch*=PROPAGATION_REQUIRED", "execute*=PROPAGATION_REQUIRED", "remove*=PROPAGATION_REQUIRED",
    //        "create*=PROPAGATION_REQUIRED", "init*=PROPAGATION_REQUIRED", "authorize*=PROPAGATION_REQUIRED",
    //        "*=PROPAGATION_REQUIRED,readOnly")).property("transactionManager", ref("attendanceTransactionManager"))

    bind("attendanceDao", classOf[TransactionProxyFactoryBean]).proxy("target", bean(classOf[HibernateEntityDao]).constructor(ref("attendanceSessionFactory")))
      .property(
        "transactionAttributes",
        props("save*=PROPAGATION_REQUIRED", "update*=PROPAGATION_REQUIRED", "delete*=PROPAGATION_REQUIRED",
          "batch*=PROPAGATION_REQUIRED", "execute*=PROPAGATION_REQUIRED", "remove*=PROPAGATION_REQUIRED",
          "create*=PROPAGATION_REQUIRED", "init*=PROPAGATION_REQUIRED", "authorize*=PROPAGATION_REQUIRED",
          "*=PROPAGATION_REQUIRED,readOnly")).property("transactionManager", ref("attendanceTransactionManager"))

    bind(classOf[AttendanceServiceImpl]).property("attendanceDao", ref("attendanceDao"))
  }
}