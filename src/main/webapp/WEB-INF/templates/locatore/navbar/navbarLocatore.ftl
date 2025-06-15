<aside class="navbar navbar-vertical navbar-expand-lg navbar-dark bg-dark" id="sidebar">
  <div class="container-fluid">
    <button
      class="navbar-toggler"
      type="button"
      data-bs-toggle="collapse"
      data-bs-target="#sidebar-menu"
      aria-controls="sidebar-menu"
      aria-expanded="false"
      aria-label="Menu principale"
    >
      <span class="navbar-toggler-icon"></span>
    </button>

    <a href="#" class="navbar-brand d-flex align-items-center py-3 py-lg-2">
      <img
       src="/CoWirking1-UFFICI/resources/images/ufficiologo2-removebg-preview%20-%20Copia.png"
        alt="Logo Cooworking"
        class="navbar-brand-image"
        style="width: 60px; height: 60px;"
        loading="lazy"
      />
      <span class="ms-2 fs-1 fw-bold">Cooworking</span>
    </a>

    <div class="collapse navbar-collapse" id="sidebar-menu">
      <ul class="navbar-nav pt-4">
        <#assign currentPage = currentPage?if_exists?string!"" >
        <li class="nav-item">
          <a class="nav-link<#if currentPage == 'home'> active</#if>" href="${ctx}/homeLocatore">
            <i class="fas fa-home me-2"></i> Home
          </a>
        </li>
        <li class="nav-item">
          <a href="${ctx}/profilo" class="nav-link<#if currentPage == 'profilo'> active</#if>">
            <i class="fas fa-user me-2"></i> Profilo
          </a>
        </li>
        <li class="nav-item">
          <a href="${ctx}/prenotazioni" class="nav-link<#if currentPage == 'prenotazioni'> active</#if>">
            <i class="fas fa-calendar-check me-2"></i> Gestione prenotazioni
          </a>
        </li>
        <li class="nav-item">
          <a href="${ctx}/gestione" class="nav-link<#if currentPage == 'uffici'> active</#if>">
            <i class="fas fa-building me-2"></i> Gestione uffici
          </a>
        </li>
        <li class="nav-item">
          <a href="${ctx}/aggiunta" class="nav-link<#if currentPage == 'aggiunta'> active</#if>">
            <i class="fas fa-plus me-2"></i> Aggiungi ufficio
          </a>
        </li>
      </ul>
    </div>
  </div>
</aside>
