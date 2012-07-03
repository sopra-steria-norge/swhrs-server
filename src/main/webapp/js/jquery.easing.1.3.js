  


<!DOCTYPE html>
<html>
  <head prefix="og: http://ogp.me/ns# fb: http://ogp.me/ns/fb# githubog: http://ogp.me/ns/fb/githubog#">
    <meta charset='utf-8'>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>jquery-mobile/experiments/scrollview/jquery.easing.1.3.js at master · jquery/jquery-mobile</title>
    <link rel="search" type="application/opensearchdescription+xml" href="/opensearch.xml" title="GitHub" />
    <link rel="fluid-icon" href="https://github.com/fluidicon.png" title="GitHub" />
    <link rel="shortcut icon" href="/favicon.ico" type="image/x-icon" />

    
    

    <meta content="authenticity_token" name="csrf-param" />
<meta content="12rxZum6mgfynERI2rOCPNUiEEFKTY5mrFmVdq2nToA=" name="csrf-token" />

    <link href="https://a248.e.akamai.net/assets.github.com/stylesheets/bundles/github-66e1073376a8ca3f5a94d0e4858971a07b9f512e.css" media="screen" rel="stylesheet" type="text/css" />
    <link href="https://a248.e.akamai.net/assets.github.com/stylesheets/bundles/github2-9a6987061a89bac7e001fbfac3a4a9e99aeb9436.css" media="screen" rel="stylesheet" type="text/css" />
    
    


    <script src="https://a248.e.akamai.net/assets.github.com/javascripts/bundles/frameworks-a450c7f907bdc1ee6b362ab1ecca811c761fd259.js" type="text/javascript"></script>
    
    <script defer="defer" src="https://a248.e.akamai.net/assets.github.com/javascripts/bundles/github-14723957ec35ef409d6171b25ca647b160a7a2d2.js" type="text/javascript"></script>
    
    

      <link rel='permalink' href='/jquery/jquery-mobile/blob/33ddcd5960ab121f891db030d44013ce5fe6b02f/experiments/scrollview/jquery.easing.1.3.js'>
    <meta property="og:title" content="jquery-mobile"/>
    <meta property="og:type" content="githubog:gitrepository"/>
    <meta property="og:url" content="https://github.com/jquery/jquery-mobile"/>
    <meta property="og:image" content="https://a248.e.akamai.net/assets.github.com/images/gravatars/gravatar-140.png?1329275856"/>
    <meta property="og:site_name" content="GitHub"/>
    <meta property="og:description" content="jQuery Mobile Framework. Contribute to jquery-mobile development by creating an account on GitHub."/>

    <meta name="description" content="jQuery Mobile Framework. Contribute to jquery-mobile development by creating an account on GitHub." />

  <link href="https://github.com/jquery/jquery-mobile/commits/master.atom" rel="alternate" title="Recent Commits to jquery-mobile:master" type="application/atom+xml" />

  </head>


  <body class="logged_in page-blob windows vis-public env-production " data-blob-contribs-enabled="yes">
    <div id="wrapper">

    
    
    

      <div id="header" class="true clearfix">
        <div class="container clearfix">
          <a class="site-logo" href="https://github.com/organizations/steria">
            <!--[if IE]>
            <img alt="GitHub" class="github-logo" src="https://a248.e.akamai.net/assets.github.com/images/modules/header/logov7.png?1323882716" />
            <img alt="GitHub" class="github-logo-hover" src="https://a248.e.akamai.net/assets.github.com/images/modules/header/logov7-hover.png?1324325358" />
            <![endif]-->
            <img alt="GitHub" class="github-logo-4x" height="30" src="https://a248.e.akamai.net/assets.github.com/images/modules/header/logov7@4x.png?1337118071" />
            <img alt="GitHub" class="github-logo-4x-hover" height="30" src="https://a248.e.akamai.net/assets.github.com/images/modules/header/logov7@4x-hover.png?1337118071" />
          </a>


              
    <div class="topsearch  ">
        <form accept-charset="UTF-8" action="/search" id="top_search_form" method="get">
  <a href="/search" class="advanced-search tooltipped downwards" title="Advanced Search"><span class="mini-icon mini-icon-advanced-search"></span></a>
  <div class="search placeholder-field js-placeholder-field">
    <input type="text" class="search my_repos_autocompleter" id="global-search-field" name="q" results="5" spellcheck="false" autocomplete="off" data-autocomplete="my-repos-autocomplete" placeholder="Search&hellip;" data-hotkey="s" />
    <div id="my-repos-autocomplete" class="autocomplete-results">
      <ul class="js-navigation-container"></ul>
    </div>
    <input type="submit" value="Search" class="button">
    <span class="mini-icon mini-icon-search-input"></span>
  </div>
  <input type="hidden" name="type" value="Everything" />
  <input type="hidden" name="repo" value="" />
  <input type="hidden" name="langOverride" value="" />
  <input type="hidden" name="start_value" value="1" />
</form>

      <ul class="top-nav">
          <li class="explore"><a href="https://github.com/explore">Explore</a></li>
          <li><a href="https://gist.github.com">Gist</a></li>
          <li><a href="/blog">Blog</a></li>
        <li><a href="http://help.github.com">Help</a></li>
      </ul>
    </div>


            


  <div id="userbox">
    <div id="user">
      <a href="https://github.com/Sorebo"><img height="20" src="https://secure.gravatar.com/avatar/6c0eb3907bc22d83ff69b41715ee1f04?s=140&amp;d=https://a248.e.akamai.net/assets.github.com%2Fimages%2Fgravatars%2Fgravatar-140.png" width="20" /></a>
      <a href="https://github.com/Sorebo" class="name">Sorebo</a>
    </div>
    <ul id="user-links">
      <li>
        <a href="/new" id="new_repo" class="tooltipped downwards" title="Create a New Repo"><span class="mini-icon mini-icon-create"></span></a>
      </li>
      <li>
        <a href="/inbox/notifications" id="notifications" class="tooltipped downwards" title="Notifications">
          <span class="mini-icon mini-icon-notifications"></span>
          <span class="unread_count">1</span>
        </a>
      </li>
      <li>
        <a href="/settings/profile" id="settings"
          class="tooltipped downwards"
          title="Account Settings ">
          <span class="mini-icon mini-icon-account-settings"></span>
        </a>
      </li>
      <li>
          <a href="/logout" data-method="post" id="logout" class="tooltipped downwards" title="Log Out">
            <span class="mini-icon mini-icon-logout"></span>
          </a>
      </li>
    </ul>
  </div>



          
        </div>
      </div>

      

            <div class="site hfeed" itemscope itemtype="http://schema.org/WebPage">
      <div class="container hentry">
        <div class="pagehead repohead instapaper_ignore readability-menu">
        <div class="title-actions-bar">
          



              <ul class="pagehead-actions">




          <li class="js-toggler-container js-social-container watch-button-container ">
            <span class="watch-button"><a href="/jquery/jquery-mobile/toggle_watch" class="minibutton btn-watch js-toggler-target lighter" data-remote="true" data-method="post" rel="nofollow">Watch</a><a class="social-count js-social-count" href="/jquery/jquery-mobile/watchers">6,781</a></span>
            <span class="unwatch-button"><a href="/jquery/jquery-mobile/toggle_watch" class="minibutton btn-unwatch js-toggler-target lighter" data-remote="true" data-method="post" rel="nofollow">Unwatch</a><a class="social-count js-social-count" href="/jquery/jquery-mobile/watchers">6,781</a></span>
          </li>

              <li>
                <a href="/jquery/jquery-mobile/fork_select" class="minibutton btn-fork js-toggler-target lighter" rel="facebox nofollow">Fork</a><a href="/jquery/jquery-mobile/network" class="social-count">1,254</a>
              </li>


    </ul>

          <h1 itemscope itemtype="http://data-vocabulary.org/Breadcrumb" class="entry-title public">
            <span class="repo-label"><span>public</span></span>
            <span class="mega-icon mega-icon-public-repo"></span>
            <span class="author vcard">
<a href="/jquery" class="url fn" itemprop="url" rel="author">              <span itemprop="title">jquery</span>
              </a></span> /
            <strong><a href="/jquery/jquery-mobile" class="js-current-repository">jquery-mobile</a></strong>
          </h1>
        </div>

          

  <ul class="tabs">
    <li><a href="/jquery/jquery-mobile" class="selected" highlight="repo_sourcerepo_downloadsrepo_commitsrepo_tagsrepo_branches">Code</a></li>
    <li><a href="/jquery/jquery-mobile/network" highlight="repo_network">Network</a>
    <li><a href="/jquery/jquery-mobile/pulls" highlight="repo_pulls">Pull Requests <span class='counter'>16</span></a></li>

      <li><a href="/jquery/jquery-mobile/issues" highlight="repo_issues">Issues <span class='counter'>350</span></a></li>

      <li><a href="/jquery/jquery-mobile/wiki" highlight="repo_wiki">Wiki</a></li>

    <li><a href="/jquery/jquery-mobile/graphs" highlight="repo_graphsrepo_contributors">Graphs</a></li>

  </ul>
 
<div class="frame frame-center tree-finder" style="display:none"
      data-tree-list-url="/jquery/jquery-mobile/tree-list/33ddcd5960ab121f891db030d44013ce5fe6b02f"
      data-blob-url-prefix="/jquery/jquery-mobile/blob/33ddcd5960ab121f891db030d44013ce5fe6b02f"
    >

  <div class="breadcrumb">
    <span class="bold"><a href="/jquery/jquery-mobile">jquery-mobile</a></span> /
    <input class="tree-finder-input js-navigation-enable" type="text" name="query" autocomplete="off" spellcheck="false">
  </div>

    <div class="octotip">
      <p>
        <a href="/jquery/jquery-mobile/dismiss-tree-finder-help" class="dismiss js-dismiss-tree-list-help" title="Hide this notice forever" rel="nofollow">Dismiss</a>
        <span class="bold">Octotip:</span> You've activated the <em>file finder</em>
        by pressing <span class="kbd">t</span> Start typing to filter the
        file list. Use <span class="kbd badmono">↑</span> and
        <span class="kbd badmono">↓</span> to navigate,
        <span class="kbd">enter</span> to view files.
      </p>
    </div>

  <table class="tree-browser" cellpadding="0" cellspacing="0">
    <tr class="js-header"><th>&nbsp;</th><th>name</th></tr>
    <tr class="js-no-results no-results" style="display: none">
      <th colspan="2">No matching files</th>
    </tr>
    <tbody class="js-results-list js-navigation-container">
    </tbody>
  </table>
</div>

<div id="jump-to-line" style="display:none">
  <h2>Jump to Line</h2>
  <form accept-charset="UTF-8">
    <input class="textfield" type="text">
    <div class="full-button">
      <button type="submit" class="classy">
        Go
      </button>
    </div>
  </form>
</div>


<div class="subnav-bar">

  <ul class="actions subnav">
    <li><a href="/jquery/jquery-mobile/tags" class="" highlight="repo_tags">Tags <span class="counter">18</span></a></li>
    <li><a href="/jquery/jquery-mobile/downloads" class="blank downloads-blank" highlight="repo_downloads">Downloads <span class="counter">0</span></a></li>
    
  </ul>

  <ul class="scope">
    <li class="switcher">

      <div class="context-menu-container js-menu-container js-context-menu">
        <a href="#"
           class="minibutton bigger switcher js-menu-target js-commitish-button btn-branch repo-tree"
           data-hotkey="w"
           data-master-branch="master"
           data-ref="master">
           <span><i>branch:</i> master</span>
        </a>

        <div class="context-pane commitish-context js-menu-content">
          <a href="javascript:;" class="close js-menu-close"><span class="mini-icon mini-icon-remove-close"></span></a>
          <div class="context-title">Switch Branches/Tags</div>
          <div class="context-body pane-selector commitish-selector js-navigation-container">
            <div class="filterbar">
              <input type="text" id="context-commitish-filter-field" class="js-navigation-enable" placeholder="Filter branches/tags" data-filterable />

              <ul class="tabs">
                <li><a href="#" data-filter="branches" class="selected">Branches</a></li>
                <li><a href="#" data-filter="tags">Tags</a></li>
              </ul>
            </div>

            <div class="js-filter-tab js-filter-branches" data-filterable-for="context-commitish-filter-field" data-filterable-type=substring>
              <div class="no-results js-not-filterable">Nothing to show</div>
                <div class="commitish-item branch-commitish selector-item js-navigation-item js-navigation-target">
                  <h4>
                      <a href="/jquery/jquery-mobile/blob/1.0-stable/experiments/scrollview/jquery.easing.1.3.js" class="js-navigation-open" data-name="1.0-stable" rel="nofollow">1.0-stable</a>
                  </h4>
                </div>
                <div class="commitish-item branch-commitish selector-item js-navigation-item js-navigation-target">
                  <h4>
                      <a href="/jquery/jquery-mobile/blob/1.1-stable/experiments/scrollview/jquery.easing.1.3.js" class="js-navigation-open" data-name="1.1-stable" rel="nofollow">1.1-stable</a>
                  </h4>
                </div>
                <div class="commitish-item branch-commitish selector-item js-navigation-item js-navigation-target">
                  <h4>
                      <a href="/jquery/jquery-mobile/blob/1.2/experiments/scrollview/jquery.easing.1.3.js" class="js-navigation-open" data-name="1.2" rel="nofollow">1.2</a>
                  </h4>
                </div>
                <div class="commitish-item branch-commitish selector-item js-navigation-item js-navigation-target">
                  <h4>
                      <a href="/jquery/jquery-mobile/blob/changepage-prevent/experiments/scrollview/jquery.easing.1.3.js" class="js-navigation-open" data-name="changepage-prevent" rel="nofollow">changepage-prevent</a>
                  </h4>
                </div>
                <div class="commitish-item branch-commitish selector-item js-navigation-item js-navigation-target">
                  <h4>
                      <a href="/jquery/jquery-mobile/blob/cssstructure/experiments/scrollview/jquery.easing.1.3.js" class="js-navigation-open" data-name="cssstructure" rel="nofollow">cssstructure</a>
                  </h4>
                </div>
                <div class="commitish-item branch-commitish selector-item js-navigation-item js-navigation-target">
                  <h4>
                      <a href="/jquery/jquery-mobile/blob/custom-select-via-popup/experiments/scrollview/jquery.easing.1.3.js" class="js-navigation-open" data-name="custom-select-via-popup" rel="nofollow">custom-select-via-popup</a>
                  </h4>
                </div>
                <div class="commitish-item branch-commitish selector-item js-navigation-item js-navigation-target">
                  <h4>
                      <a href="/jquery/jquery-mobile/blob/data-driven-with-fallback/experiments/scrollview/jquery.easing.1.3.js" class="js-navigation-open" data-name="data-driven-with-fallback" rel="nofollow">data-driven-with-fallback</a>
                  </h4>
                </div>
                <div class="commitish-item branch-commitish selector-item js-navigation-item js-navigation-target">
                  <h4>
                      <a href="/jquery/jquery-mobile/blob/fetchlink/experiments/scrollview/jquery.easing.1.3.js" class="js-navigation-open" data-name="fetchlink" rel="nofollow">fetchlink</a>
                  </h4>
                </div>
                <div class="commitish-item branch-commitish selector-item js-navigation-item js-navigation-target">
                  <h4>
                      <a href="/jquery/jquery-mobile/blob/fix-4423/experiments/scrollview/jquery.easing.1.3.js" class="js-navigation-open" data-name="fix-4423" rel="nofollow">fix-4423</a>
                  </h4>
                </div>
                <div class="commitish-item branch-commitish selector-item js-navigation-item js-navigation-target">
                  <h4>
                      <a href="/jquery/jquery-mobile/blob/fixedtoolbar-polyfill/experiments/scrollview/jquery.easing.1.3.js" class="js-navigation-open" data-name="fixedtoolbar-polyfill" rel="nofollow">fixedtoolbar-polyfill</a>
                  </h4>
                </div>
                <div class="commitish-item branch-commitish selector-item js-navigation-item js-navigation-target">
                  <h4>
                      <a href="/jquery/jquery-mobile/blob/grunt/experiments/scrollview/jquery.easing.1.3.js" class="js-navigation-open" data-name="grunt" rel="nofollow">grunt</a>
                  </h4>
                </div>
                <div class="commitish-item branch-commitish selector-item js-navigation-item js-navigation-target">
                  <h4>
                      <a href="/jquery/jquery-mobile/blob/list-perf/experiments/scrollview/jquery.easing.1.3.js" class="js-navigation-open" data-name="list-perf" rel="nofollow">list-perf</a>
                  </h4>
                </div>
                <div class="commitish-item branch-commitish selector-item js-navigation-item js-navigation-target">
                  <h4>
                      <a href="/jquery/jquery-mobile/blob/master/experiments/scrollview/jquery.easing.1.3.js" class="js-navigation-open" data-name="master" rel="nofollow">master</a>
                  </h4>
                </div>
                <div class="commitish-item branch-commitish selector-item js-navigation-item js-navigation-target">
                  <h4>
                      <a href="/jquery/jquery-mobile/blob/nav-ready-deferred/experiments/scrollview/jquery.easing.1.3.js" class="js-navigation-open" data-name="nav-ready-deferred" rel="nofollow">nav-ready-deferred</a>
                  </h4>
                </div>
                <div class="commitish-item branch-commitish selector-item js-navigation-item js-navigation-target">
                  <h4>
                      <a href="/jquery/jquery-mobile/blob/new-readme/experiments/scrollview/jquery.easing.1.3.js" class="js-navigation-open" data-name="new-readme" rel="nofollow">new-readme</a>
                  </h4>
                </div>
                <div class="commitish-item branch-commitish selector-item js-navigation-item js-navigation-target">
                  <h4>
                      <a href="/jquery/jquery-mobile/blob/popup-widget/experiments/scrollview/jquery.easing.1.3.js" class="js-navigation-open" data-name="popup-widget" rel="nofollow">popup-widget</a>
                  </h4>
                </div>
                <div class="commitish-item branch-commitish selector-item js-navigation-item js-navigation-target">
                  <h4>
                      <a href="/jquery/jquery-mobile/blob/toolbar-persist/experiments/scrollview/jquery.easing.1.3.js" class="js-navigation-open" data-name="toolbar-persist" rel="nofollow">toolbar-persist</a>
                  </h4>
                </div>
                <div class="commitish-item branch-commitish selector-item js-navigation-item js-navigation-target">
                  <h4>
                      <a href="/jquery/jquery-mobile/blob/transitions-sequential-simultaneous/experiments/scrollview/jquery.easing.1.3.js" class="js-navigation-open" data-name="transitions-sequential-simultaneous" rel="nofollow">transitions-sequential-simultaneous</a>
                  </h4>
                </div>
                <div class="commitish-item branch-commitish selector-item js-navigation-item js-navigation-target">
                  <h4>
                      <a href="/jquery/jquery-mobile/blob/ui-tabs-experiment/experiments/scrollview/jquery.easing.1.3.js" class="js-navigation-open" data-name="ui-tabs-experiment" rel="nofollow">ui-tabs-experiment</a>
                  </h4>
                </div>
            </div>

            <div class="js-filter-tab js-filter-tags" style="display:none" data-filterable-for="context-commitish-filter-field" data-filterable-type=substring>
              <div class="no-results js-not-filterable">Nothing to show</div>
                <div class="commitish-item tag-commitish selector-item js-navigation-item js-navigation-target">
                  <h4>
                      <a href="/jquery/jquery-mobile/blob/1.2.0-pre/experiments/scrollview/jquery.easing.1.3.js" class="js-navigation-open" data-name="1.2.0-pre" rel="nofollow">1.2.0-pre</a>
                  </h4>
                </div>
                <div class="commitish-item tag-commitish selector-item js-navigation-item js-navigation-target">
                  <h4>
                      <a href="/jquery/jquery-mobile/blob/1.1.1-rc.1/experiments/scrollview/jquery.easing.1.3.js" class="js-navigation-open" data-name="1.1.1-rc.1" rel="nofollow">1.1.1-rc.1</a>
                  </h4>
                </div>
                <div class="commitish-item tag-commitish selector-item js-navigation-item js-navigation-target">
                  <h4>
                      <a href="/jquery/jquery-mobile/blob/1.1.0-rc.2/experiments/scrollview/jquery.easing.1.3.js" class="js-navigation-open" data-name="1.1.0-rc.2" rel="nofollow">1.1.0-rc.2</a>
                  </h4>
                </div>
                <div class="commitish-item tag-commitish selector-item js-navigation-item js-navigation-target">
                  <h4>
                      <a href="/jquery/jquery-mobile/blob/1.1.0-rc.1/experiments/scrollview/jquery.easing.1.3.js" class="js-navigation-open" data-name="1.1.0-rc.1" rel="nofollow">1.1.0-rc.1</a>
                  </h4>
                </div>
                <div class="commitish-item tag-commitish selector-item js-navigation-item js-navigation-target">
                  <h4>
                      <a href="/jquery/jquery-mobile/blob/1.1.0/experiments/scrollview/jquery.easing.1.3.js" class="js-navigation-open" data-name="1.1.0" rel="nofollow">1.1.0</a>
                  </h4>
                </div>
                <div class="commitish-item tag-commitish selector-item js-navigation-item js-navigation-target">
                  <h4>
                      <a href="/jquery/jquery-mobile/blob/1.0rc3/experiments/scrollview/jquery.easing.1.3.js" class="js-navigation-open" data-name="1.0rc3" rel="nofollow">1.0rc3</a>
                  </h4>
                </div>
                <div class="commitish-item tag-commitish selector-item js-navigation-item js-navigation-target">
                  <h4>
                      <a href="/jquery/jquery-mobile/blob/1.0rc2/experiments/scrollview/jquery.easing.1.3.js" class="js-navigation-open" data-name="1.0rc2" rel="nofollow">1.0rc2</a>
                  </h4>
                </div>
                <div class="commitish-item tag-commitish selector-item js-navigation-item js-navigation-target">
                  <h4>
                      <a href="/jquery/jquery-mobile/blob/1.0rc1/experiments/scrollview/jquery.easing.1.3.js" class="js-navigation-open" data-name="1.0rc1" rel="nofollow">1.0rc1</a>
                  </h4>
                </div>
                <div class="commitish-item tag-commitish selector-item js-navigation-item js-navigation-target">
                  <h4>
                      <a href="/jquery/jquery-mobile/blob/1.0b3/experiments/scrollview/jquery.easing.1.3.js" class="js-navigation-open" data-name="1.0b3" rel="nofollow">1.0b3</a>
                  </h4>
                </div>
                <div class="commitish-item tag-commitish selector-item js-navigation-item js-navigation-target">
                  <h4>
                      <a href="/jquery/jquery-mobile/blob/1.0b2/experiments/scrollview/jquery.easing.1.3.js" class="js-navigation-open" data-name="1.0b2" rel="nofollow">1.0b2</a>
                  </h4>
                </div>
                <div class="commitish-item tag-commitish selector-item js-navigation-item js-navigation-target">
                  <h4>
                      <a href="/jquery/jquery-mobile/blob/1.0b1/experiments/scrollview/jquery.easing.1.3.js" class="js-navigation-open" data-name="1.0b1" rel="nofollow">1.0b1</a>
                  </h4>
                </div>
                <div class="commitish-item tag-commitish selector-item js-navigation-item js-navigation-target">
                  <h4>
                      <a href="/jquery/jquery-mobile/blob/1.0a4.1/experiments/scrollview/jquery.easing.1.3.js" class="js-navigation-open" data-name="1.0a4.1" rel="nofollow">1.0a4.1</a>
                  </h4>
                </div>
                <div class="commitish-item tag-commitish selector-item js-navigation-item js-navigation-target">
                  <h4>
                      <a href="/jquery/jquery-mobile/blob/1.0a4/experiments/scrollview/jquery.easing.1.3.js" class="js-navigation-open" data-name="1.0a4" rel="nofollow">1.0a4</a>
                  </h4>
                </div>
                <div class="commitish-item tag-commitish selector-item js-navigation-item js-navigation-target">
                  <h4>
                      <a href="/jquery/jquery-mobile/blob/1.0a3/experiments/scrollview/jquery.easing.1.3.js" class="js-navigation-open" data-name="1.0a3" rel="nofollow">1.0a3</a>
                  </h4>
                </div>
                <div class="commitish-item tag-commitish selector-item js-navigation-item js-navigation-target">
                  <h4>
                      <a href="/jquery/jquery-mobile/blob/1.0a2/experiments/scrollview/jquery.easing.1.3.js" class="js-navigation-open" data-name="1.0a2" rel="nofollow">1.0a2</a>
                  </h4>
                </div>
                <div class="commitish-item tag-commitish selector-item js-navigation-item js-navigation-target">
                  <h4>
                      <a href="/jquery/jquery-mobile/blob/1.0a1/experiments/scrollview/jquery.easing.1.3.js" class="js-navigation-open" data-name="1.0a1" rel="nofollow">1.0a1</a>
                  </h4>
                </div>
                <div class="commitish-item tag-commitish selector-item js-navigation-item js-navigation-target">
                  <h4>
                      <a href="/jquery/jquery-mobile/blob/1.0.1/experiments/scrollview/jquery.easing.1.3.js" class="js-navigation-open" data-name="1.0.1" rel="nofollow">1.0.1</a>
                  </h4>
                </div>
                <div class="commitish-item tag-commitish selector-item js-navigation-item js-navigation-target">
                  <h4>
                      <a href="/jquery/jquery-mobile/blob/1.0/experiments/scrollview/jquery.easing.1.3.js" class="js-navigation-open" data-name="1.0" rel="nofollow">1.0</a>
                  </h4>
                </div>
            </div>
          </div>
        </div><!-- /.commitish-context-context -->
      </div>

    </li>
  </ul>

  <ul class="subnav with-scope">

    <li><a href="/jquery/jquery-mobile" class="selected" highlight="repo_source">Files</a></li>
    <li><a href="/jquery/jquery-mobile/commits/master" highlight="repo_commits">Commits</a></li>
    <li><a href="/jquery/jquery-mobile/branches" class="" highlight="repo_branches" rel="nofollow">Branches <span class="counter">19</span></a></li>
  </ul>

</div>

  
  
  


          

        </div><!-- /.repohead -->

        





<!-- block_view_fragment_key: views10/v8/blob:v21:2bbf635cff6f567a79460137e077071a -->
  <div id="slider">

    <div class="breadcrumb" data-path="experiments/scrollview/jquery.easing.1.3.js/">
      <b itemscope="" itemtype="http://data-vocabulary.org/Breadcrumb"><a href="/jquery/jquery-mobile/tree/09925129166a2fa62bb96e3244e82bef26dacdb3" class="js-rewrite-sha" itemprop="url"><span itemprop="title">jquery-mobile</span></a></b> / <span itemscope="" itemtype="http://data-vocabulary.org/Breadcrumb"><a href="/jquery/jquery-mobile/tree/09925129166a2fa62bb96e3244e82bef26dacdb3/experiments" class="js-rewrite-sha" itemscope="url"><span itemprop="title">experiments</span></a></span> / <span itemscope="" itemtype="http://data-vocabulary.org/Breadcrumb"><a href="/jquery/jquery-mobile/tree/09925129166a2fa62bb96e3244e82bef26dacdb3/experiments/scrollview" class="js-rewrite-sha" itemscope="url"><span itemprop="title">scrollview</span></a></span> / <strong class="final-path">jquery.easing.1.3.js</strong> <span class="js-clippy mini-icon mini-icon-clippy " data-clipboard-text="experiments/scrollview/jquery.easing.1.3.js" data-copied-hint="copied!" data-copy-hint="copy to clipboard"></span>
    </div>


      <div class="commit file-history-tease" data-path="experiments/scrollview/jquery.easing.1.3.js/">
        <img class="main-avatar" height="24" src="https://secure.gravatar.com/avatar/fa72320f7fd01aa7d743768f85ccf9ea?s=140&amp;d=https://a248.e.akamai.net/assets.github.com%2Fimages%2Fgravatars%2Fgravatar-140.png" width="24" />
        <span class="author"><a href="/jblas">jblas</a></span>
        <time class="js-relative-date" datetime="2010-11-18T15:12:27-08:00" title="2010-11-18 15:12:27">November 18, 2010</time>
        <div class="commit-title">
            <a href="/jquery/jquery-mobile/commit/dbdfd23541924bf14142f985ef5093789f4a460f" class="message">Initial checkin of experimental support for momentum scrolling. We'd …</a>
        </div>

        <div class="participation">
          <p class="quickstat"><a href="#blob_contributors_box" rel="facebox"><strong>1</strong> contributor</a></p>
          
        </div>
        <div id="blob_contributors_box" style="display:none">
          <h2>Users on GitHub who have contributed to this file</h2>
          <ul class="facebox-user-list">
            <li>
              <img height="24" src="https://secure.gravatar.com/avatar/fa72320f7fd01aa7d743768f85ccf9ea?s=140&amp;d=https://a248.e.akamai.net/assets.github.com%2Fimages%2Fgravatars%2Fgravatar-140.png" width="24" />
              <a href="/jblas">jblas</a>
            </li>
          </ul>
        </div>
      </div>

    <div class="frames">
      <div class="frame frame-center" data-path="experiments/scrollview/jquery.easing.1.3.js/" data-permalink-url="/jquery/jquery-mobile/blob/09925129166a2fa62bb96e3244e82bef26dacdb3/experiments/scrollview/jquery.easing.1.3.js" data-title="jquery-mobile/experiments/scrollview/jquery.easing.1.3.js at master · jquery/jquery-mobile · GitHub" data-type="blob">

        <div id="files" class="bubble">
          <div class="file">
            <div class="meta">
              <div class="info">
                <span class="icon"><b class="mini-icon mini-icon-text-file"></b></span>
                <span class="mode" title="File Mode">100644</span>
                  <span>205 lines (202 sloc)</span>
                <span>8.097 kb</span>
              </div>
              <ul class="button-group actions">
                  <li>
                    <a class="grouped-button file-edit-link minibutton bigger lighter js-rewrite-sha" href="/jquery/jquery-mobile/edit/09925129166a2fa62bb96e3244e82bef26dacdb3/experiments/scrollview/jquery.easing.1.3.js" data-method="post" rel="nofollow" data-hotkey="e">Edit this file</a>
                  </li>

                <li>
                  <a href="/jquery/jquery-mobile/raw/master/experiments/scrollview/jquery.easing.1.3.js" class="minibutton btn-raw grouped-button bigger lighter" id="raw-url"><span class="icon"></span>Raw</a>
                </li>
                  <li>
                    <a href="/jquery/jquery-mobile/blame/master/experiments/scrollview/jquery.easing.1.3.js" class="minibutton btn-blame grouped-button bigger lighter"><span class="icon"></span>Blame</a>
                  </li>
                <li>
                  <a href="/jquery/jquery-mobile/commits/master/experiments/scrollview/jquery.easing.1.3.js" class="minibutton btn-history grouped-button bigger lighter" rel="nofollow"><span class="icon"></span>History</a>
                </li>
              </ul>
            </div>
              <div class="data type-javascript">
      <table cellpadding="0" cellspacing="0" class="lines">
        <tr>
          <td>
            <pre class="line_numbers"><span id="L1" rel="#L1">1</span>
<span id="L2" rel="#L2">2</span>
<span id="L3" rel="#L3">3</span>
<span id="L4" rel="#L4">4</span>
<span id="L5" rel="#L5">5</span>
<span id="L6" rel="#L6">6</span>
<span id="L7" rel="#L7">7</span>
<span id="L8" rel="#L8">8</span>
<span id="L9" rel="#L9">9</span>
<span id="L10" rel="#L10">10</span>
<span id="L11" rel="#L11">11</span>
<span id="L12" rel="#L12">12</span>
<span id="L13" rel="#L13">13</span>
<span id="L14" rel="#L14">14</span>
<span id="L15" rel="#L15">15</span>
<span id="L16" rel="#L16">16</span>
<span id="L17" rel="#L17">17</span>
<span id="L18" rel="#L18">18</span>
<span id="L19" rel="#L19">19</span>
<span id="L20" rel="#L20">20</span>
<span id="L21" rel="#L21">21</span>
<span id="L22" rel="#L22">22</span>
<span id="L23" rel="#L23">23</span>
<span id="L24" rel="#L24">24</span>
<span id="L25" rel="#L25">25</span>
<span id="L26" rel="#L26">26</span>
<span id="L27" rel="#L27">27</span>
<span id="L28" rel="#L28">28</span>
<span id="L29" rel="#L29">29</span>
<span id="L30" rel="#L30">30</span>
<span id="L31" rel="#L31">31</span>
<span id="L32" rel="#L32">32</span>
<span id="L33" rel="#L33">33</span>
<span id="L34" rel="#L34">34</span>
<span id="L35" rel="#L35">35</span>
<span id="L36" rel="#L36">36</span>
<span id="L37" rel="#L37">37</span>
<span id="L38" rel="#L38">38</span>
<span id="L39" rel="#L39">39</span>
<span id="L40" rel="#L40">40</span>
<span id="L41" rel="#L41">41</span>
<span id="L42" rel="#L42">42</span>
<span id="L43" rel="#L43">43</span>
<span id="L44" rel="#L44">44</span>
<span id="L45" rel="#L45">45</span>
<span id="L46" rel="#L46">46</span>
<span id="L47" rel="#L47">47</span>
<span id="L48" rel="#L48">48</span>
<span id="L49" rel="#L49">49</span>
<span id="L50" rel="#L50">50</span>
<span id="L51" rel="#L51">51</span>
<span id="L52" rel="#L52">52</span>
<span id="L53" rel="#L53">53</span>
<span id="L54" rel="#L54">54</span>
<span id="L55" rel="#L55">55</span>
<span id="L56" rel="#L56">56</span>
<span id="L57" rel="#L57">57</span>
<span id="L58" rel="#L58">58</span>
<span id="L59" rel="#L59">59</span>
<span id="L60" rel="#L60">60</span>
<span id="L61" rel="#L61">61</span>
<span id="L62" rel="#L62">62</span>
<span id="L63" rel="#L63">63</span>
<span id="L64" rel="#L64">64</span>
<span id="L65" rel="#L65">65</span>
<span id="L66" rel="#L66">66</span>
<span id="L67" rel="#L67">67</span>
<span id="L68" rel="#L68">68</span>
<span id="L69" rel="#L69">69</span>
<span id="L70" rel="#L70">70</span>
<span id="L71" rel="#L71">71</span>
<span id="L72" rel="#L72">72</span>
<span id="L73" rel="#L73">73</span>
<span id="L74" rel="#L74">74</span>
<span id="L75" rel="#L75">75</span>
<span id="L76" rel="#L76">76</span>
<span id="L77" rel="#L77">77</span>
<span id="L78" rel="#L78">78</span>
<span id="L79" rel="#L79">79</span>
<span id="L80" rel="#L80">80</span>
<span id="L81" rel="#L81">81</span>
<span id="L82" rel="#L82">82</span>
<span id="L83" rel="#L83">83</span>
<span id="L84" rel="#L84">84</span>
<span id="L85" rel="#L85">85</span>
<span id="L86" rel="#L86">86</span>
<span id="L87" rel="#L87">87</span>
<span id="L88" rel="#L88">88</span>
<span id="L89" rel="#L89">89</span>
<span id="L90" rel="#L90">90</span>
<span id="L91" rel="#L91">91</span>
<span id="L92" rel="#L92">92</span>
<span id="L93" rel="#L93">93</span>
<span id="L94" rel="#L94">94</span>
<span id="L95" rel="#L95">95</span>
<span id="L96" rel="#L96">96</span>
<span id="L97" rel="#L97">97</span>
<span id="L98" rel="#L98">98</span>
<span id="L99" rel="#L99">99</span>
<span id="L100" rel="#L100">100</span>
<span id="L101" rel="#L101">101</span>
<span id="L102" rel="#L102">102</span>
<span id="L103" rel="#L103">103</span>
<span id="L104" rel="#L104">104</span>
<span id="L105" rel="#L105">105</span>
<span id="L106" rel="#L106">106</span>
<span id="L107" rel="#L107">107</span>
<span id="L108" rel="#L108">108</span>
<span id="L109" rel="#L109">109</span>
<span id="L110" rel="#L110">110</span>
<span id="L111" rel="#L111">111</span>
<span id="L112" rel="#L112">112</span>
<span id="L113" rel="#L113">113</span>
<span id="L114" rel="#L114">114</span>
<span id="L115" rel="#L115">115</span>
<span id="L116" rel="#L116">116</span>
<span id="L117" rel="#L117">117</span>
<span id="L118" rel="#L118">118</span>
<span id="L119" rel="#L119">119</span>
<span id="L120" rel="#L120">120</span>
<span id="L121" rel="#L121">121</span>
<span id="L122" rel="#L122">122</span>
<span id="L123" rel="#L123">123</span>
<span id="L124" rel="#L124">124</span>
<span id="L125" rel="#L125">125</span>
<span id="L126" rel="#L126">126</span>
<span id="L127" rel="#L127">127</span>
<span id="L128" rel="#L128">128</span>
<span id="L129" rel="#L129">129</span>
<span id="L130" rel="#L130">130</span>
<span id="L131" rel="#L131">131</span>
<span id="L132" rel="#L132">132</span>
<span id="L133" rel="#L133">133</span>
<span id="L134" rel="#L134">134</span>
<span id="L135" rel="#L135">135</span>
<span id="L136" rel="#L136">136</span>
<span id="L137" rel="#L137">137</span>
<span id="L138" rel="#L138">138</span>
<span id="L139" rel="#L139">139</span>
<span id="L140" rel="#L140">140</span>
<span id="L141" rel="#L141">141</span>
<span id="L142" rel="#L142">142</span>
<span id="L143" rel="#L143">143</span>
<span id="L144" rel="#L144">144</span>
<span id="L145" rel="#L145">145</span>
<span id="L146" rel="#L146">146</span>
<span id="L147" rel="#L147">147</span>
<span id="L148" rel="#L148">148</span>
<span id="L149" rel="#L149">149</span>
<span id="L150" rel="#L150">150</span>
<span id="L151" rel="#L151">151</span>
<span id="L152" rel="#L152">152</span>
<span id="L153" rel="#L153">153</span>
<span id="L154" rel="#L154">154</span>
<span id="L155" rel="#L155">155</span>
<span id="L156" rel="#L156">156</span>
<span id="L157" rel="#L157">157</span>
<span id="L158" rel="#L158">158</span>
<span id="L159" rel="#L159">159</span>
<span id="L160" rel="#L160">160</span>
<span id="L161" rel="#L161">161</span>
<span id="L162" rel="#L162">162</span>
<span id="L163" rel="#L163">163</span>
<span id="L164" rel="#L164">164</span>
<span id="L165" rel="#L165">165</span>
<span id="L166" rel="#L166">166</span>
<span id="L167" rel="#L167">167</span>
<span id="L168" rel="#L168">168</span>
<span id="L169" rel="#L169">169</span>
<span id="L170" rel="#L170">170</span>
<span id="L171" rel="#L171">171</span>
<span id="L172" rel="#L172">172</span>
<span id="L173" rel="#L173">173</span>
<span id="L174" rel="#L174">174</span>
<span id="L175" rel="#L175">175</span>
<span id="L176" rel="#L176">176</span>
<span id="L177" rel="#L177">177</span>
<span id="L178" rel="#L178">178</span>
<span id="L179" rel="#L179">179</span>
<span id="L180" rel="#L180">180</span>
<span id="L181" rel="#L181">181</span>
<span id="L182" rel="#L182">182</span>
<span id="L183" rel="#L183">183</span>
<span id="L184" rel="#L184">184</span>
<span id="L185" rel="#L185">185</span>
<span id="L186" rel="#L186">186</span>
<span id="L187" rel="#L187">187</span>
<span id="L188" rel="#L188">188</span>
<span id="L189" rel="#L189">189</span>
<span id="L190" rel="#L190">190</span>
<span id="L191" rel="#L191">191</span>
<span id="L192" rel="#L192">192</span>
<span id="L193" rel="#L193">193</span>
<span id="L194" rel="#L194">194</span>
<span id="L195" rel="#L195">195</span>
<span id="L196" rel="#L196">196</span>
<span id="L197" rel="#L197">197</span>
<span id="L198" rel="#L198">198</span>
<span id="L199" rel="#L199">199</span>
<span id="L200" rel="#L200">200</span>
<span id="L201" rel="#L201">201</span>
<span id="L202" rel="#L202">202</span>
<span id="L203" rel="#L203">203</span>
<span id="L204" rel="#L204">204</span>
<span id="L205" rel="#L205">205</span>
</pre>
          </td>
          <td width="100%">
                <div class="highlight"><pre><div class='line' id='LC1'><span class="cm">/*</span></div><div class='line' id='LC2'><span class="cm"> * jQuery Easing v1.3 - http://gsgd.co.uk/sandbox/jquery/easing/</span></div><div class='line' id='LC3'><span class="cm"> *</span></div><div class='line' id='LC4'><span class="cm"> * Uses the built in easing capabilities added In jQuery 1.1</span></div><div class='line' id='LC5'><span class="cm"> * to offer multiple easing options</span></div><div class='line' id='LC6'><span class="cm"> *</span></div><div class='line' id='LC7'><span class="cm"> * TERMS OF USE - jQuery Easing</span></div><div class='line' id='LC8'><span class="cm"> * </span></div><div class='line' id='LC9'><span class="cm"> * Open source under the BSD License. </span></div><div class='line' id='LC10'><span class="cm"> * </span></div><div class='line' id='LC11'><span class="cm"> * Copyright © 2008 George McGinley Smith</span></div><div class='line' id='LC12'><span class="cm"> * All rights reserved.</span></div><div class='line' id='LC13'><span class="cm"> * </span></div><div class='line' id='LC14'><span class="cm"> * Redistribution and use in source and binary forms, with or without modification, </span></div><div class='line' id='LC15'><span class="cm"> * are permitted provided that the following conditions are met:</span></div><div class='line' id='LC16'><span class="cm"> * </span></div><div class='line' id='LC17'><span class="cm"> * Redistributions of source code must retain the above copyright notice, this list of </span></div><div class='line' id='LC18'><span class="cm"> * conditions and the following disclaimer.</span></div><div class='line' id='LC19'><span class="cm"> * Redistributions in binary form must reproduce the above copyright notice, this list </span></div><div class='line' id='LC20'><span class="cm"> * of conditions and the following disclaimer in the documentation and/or other materials </span></div><div class='line' id='LC21'><span class="cm"> * provided with the distribution.</span></div><div class='line' id='LC22'><span class="cm"> * </span></div><div class='line' id='LC23'><span class="cm"> * Neither the name of the author nor the names of contributors may be used to endorse </span></div><div class='line' id='LC24'><span class="cm"> * or promote products derived from this software without specific prior written permission.</span></div><div class='line' id='LC25'><span class="cm"> * </span></div><div class='line' id='LC26'><span class="cm"> * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS &quot;AS IS&quot; AND ANY </span></div><div class='line' id='LC27'><span class="cm"> * EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF</span></div><div class='line' id='LC28'><span class="cm"> * MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE</span></div><div class='line' id='LC29'><span class="cm"> *  COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,</span></div><div class='line' id='LC30'><span class="cm"> *  EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE</span></div><div class='line' id='LC31'><span class="cm"> *  GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED </span></div><div class='line' id='LC32'><span class="cm"> * AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING</span></div><div class='line' id='LC33'><span class="cm"> *  NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED </span></div><div class='line' id='LC34'><span class="cm"> * OF THE POSSIBILITY OF SUCH DAMAGE. </span></div><div class='line' id='LC35'><span class="cm"> *</span></div><div class='line' id='LC36'><span class="cm">*/</span></div><div class='line' id='LC37'><br/></div><div class='line' id='LC38'><span class="c1">// t: current time, b: begInnIng value, c: change In value, d: duration</span></div><div class='line' id='LC39'><span class="nx">jQuery</span><span class="p">.</span><span class="nx">easing</span><span class="p">[</span><span class="s1">&#39;jswing&#39;</span><span class="p">]</span> <span class="o">=</span> <span class="nx">jQuery</span><span class="p">.</span><span class="nx">easing</span><span class="p">[</span><span class="s1">&#39;swing&#39;</span><span class="p">];</span></div><div class='line' id='LC40'><br/></div><div class='line' id='LC41'><span class="nx">jQuery</span><span class="p">.</span><span class="nx">extend</span><span class="p">(</span> <span class="nx">jQuery</span><span class="p">.</span><span class="nx">easing</span><span class="p">,</span></div><div class='line' id='LC42'><span class="p">{</span></div><div class='line' id='LC43'>	<span class="nx">def</span><span class="o">:</span> <span class="s1">&#39;easeOutQuad&#39;</span><span class="p">,</span></div><div class='line' id='LC44'>	<span class="nx">swing</span><span class="o">:</span> <span class="kd">function</span> <span class="p">(</span><span class="nx">x</span><span class="p">,</span> <span class="nx">t</span><span class="p">,</span> <span class="nx">b</span><span class="p">,</span> <span class="nx">c</span><span class="p">,</span> <span class="nx">d</span><span class="p">)</span> <span class="p">{</span></div><div class='line' id='LC45'>		<span class="c1">//alert(jQuery.easing.default);</span></div><div class='line' id='LC46'>		<span class="k">return</span> <span class="nx">jQuery</span><span class="p">.</span><span class="nx">easing</span><span class="p">[</span><span class="nx">jQuery</span><span class="p">.</span><span class="nx">easing</span><span class="p">.</span><span class="nx">def</span><span class="p">](</span><span class="nx">x</span><span class="p">,</span> <span class="nx">t</span><span class="p">,</span> <span class="nx">b</span><span class="p">,</span> <span class="nx">c</span><span class="p">,</span> <span class="nx">d</span><span class="p">);</span></div><div class='line' id='LC47'>	<span class="p">},</span></div><div class='line' id='LC48'>	<span class="nx">easeInQuad</span><span class="o">:</span> <span class="kd">function</span> <span class="p">(</span><span class="nx">x</span><span class="p">,</span> <span class="nx">t</span><span class="p">,</span> <span class="nx">b</span><span class="p">,</span> <span class="nx">c</span><span class="p">,</span> <span class="nx">d</span><span class="p">)</span> <span class="p">{</span></div><div class='line' id='LC49'>		<span class="k">return</span> <span class="nx">c</span><span class="o">*</span><span class="p">(</span><span class="nx">t</span><span class="o">/=</span><span class="nx">d</span><span class="p">)</span><span class="o">*</span><span class="nx">t</span> <span class="o">+</span> <span class="nx">b</span><span class="p">;</span></div><div class='line' id='LC50'>	<span class="p">},</span></div><div class='line' id='LC51'>	<span class="nx">easeOutQuad</span><span class="o">:</span> <span class="kd">function</span> <span class="p">(</span><span class="nx">x</span><span class="p">,</span> <span class="nx">t</span><span class="p">,</span> <span class="nx">b</span><span class="p">,</span> <span class="nx">c</span><span class="p">,</span> <span class="nx">d</span><span class="p">)</span> <span class="p">{</span></div><div class='line' id='LC52'>		<span class="k">return</span> <span class="o">-</span><span class="nx">c</span> <span class="o">*</span><span class="p">(</span><span class="nx">t</span><span class="o">/=</span><span class="nx">d</span><span class="p">)</span><span class="o">*</span><span class="p">(</span><span class="nx">t</span><span class="o">-</span><span class="mi">2</span><span class="p">)</span> <span class="o">+</span> <span class="nx">b</span><span class="p">;</span></div><div class='line' id='LC53'>	<span class="p">},</span></div><div class='line' id='LC54'>	<span class="nx">easeInOutQuad</span><span class="o">:</span> <span class="kd">function</span> <span class="p">(</span><span class="nx">x</span><span class="p">,</span> <span class="nx">t</span><span class="p">,</span> <span class="nx">b</span><span class="p">,</span> <span class="nx">c</span><span class="p">,</span> <span class="nx">d</span><span class="p">)</span> <span class="p">{</span></div><div class='line' id='LC55'>		<span class="k">if</span> <span class="p">((</span><span class="nx">t</span><span class="o">/=</span><span class="nx">d</span><span class="o">/</span><span class="mi">2</span><span class="p">)</span> <span class="o">&lt;</span> <span class="mi">1</span><span class="p">)</span> <span class="k">return</span> <span class="nx">c</span><span class="o">/</span><span class="mi">2</span><span class="o">*</span><span class="nx">t</span><span class="o">*</span><span class="nx">t</span> <span class="o">+</span> <span class="nx">b</span><span class="p">;</span></div><div class='line' id='LC56'>		<span class="k">return</span> <span class="o">-</span><span class="nx">c</span><span class="o">/</span><span class="mi">2</span> <span class="o">*</span> <span class="p">((</span><span class="o">--</span><span class="nx">t</span><span class="p">)</span><span class="o">*</span><span class="p">(</span><span class="nx">t</span><span class="o">-</span><span class="mi">2</span><span class="p">)</span> <span class="o">-</span> <span class="mi">1</span><span class="p">)</span> <span class="o">+</span> <span class="nx">b</span><span class="p">;</span></div><div class='line' id='LC57'>	<span class="p">},</span></div><div class='line' id='LC58'>	<span class="nx">easeInCubic</span><span class="o">:</span> <span class="kd">function</span> <span class="p">(</span><span class="nx">x</span><span class="p">,</span> <span class="nx">t</span><span class="p">,</span> <span class="nx">b</span><span class="p">,</span> <span class="nx">c</span><span class="p">,</span> <span class="nx">d</span><span class="p">)</span> <span class="p">{</span></div><div class='line' id='LC59'>		<span class="k">return</span> <span class="nx">c</span><span class="o">*</span><span class="p">(</span><span class="nx">t</span><span class="o">/=</span><span class="nx">d</span><span class="p">)</span><span class="o">*</span><span class="nx">t</span><span class="o">*</span><span class="nx">t</span> <span class="o">+</span> <span class="nx">b</span><span class="p">;</span></div><div class='line' id='LC60'>	<span class="p">},</span></div><div class='line' id='LC61'>	<span class="nx">easeOutCubic</span><span class="o">:</span> <span class="kd">function</span> <span class="p">(</span><span class="nx">x</span><span class="p">,</span> <span class="nx">t</span><span class="p">,</span> <span class="nx">b</span><span class="p">,</span> <span class="nx">c</span><span class="p">,</span> <span class="nx">d</span><span class="p">)</span> <span class="p">{</span></div><div class='line' id='LC62'>		<span class="k">return</span> <span class="nx">c</span><span class="o">*</span><span class="p">((</span><span class="nx">t</span><span class="o">=</span><span class="nx">t</span><span class="o">/</span><span class="nx">d</span><span class="o">-</span><span class="mi">1</span><span class="p">)</span><span class="o">*</span><span class="nx">t</span><span class="o">*</span><span class="nx">t</span> <span class="o">+</span> <span class="mi">1</span><span class="p">)</span> <span class="o">+</span> <span class="nx">b</span><span class="p">;</span></div><div class='line' id='LC63'>	<span class="p">},</span></div><div class='line' id='LC64'>	<span class="nx">easeInOutCubic</span><span class="o">:</span> <span class="kd">function</span> <span class="p">(</span><span class="nx">x</span><span class="p">,</span> <span class="nx">t</span><span class="p">,</span> <span class="nx">b</span><span class="p">,</span> <span class="nx">c</span><span class="p">,</span> <span class="nx">d</span><span class="p">)</span> <span class="p">{</span></div><div class='line' id='LC65'>		<span class="k">if</span> <span class="p">((</span><span class="nx">t</span><span class="o">/=</span><span class="nx">d</span><span class="o">/</span><span class="mi">2</span><span class="p">)</span> <span class="o">&lt;</span> <span class="mi">1</span><span class="p">)</span> <span class="k">return</span> <span class="nx">c</span><span class="o">/</span><span class="mi">2</span><span class="o">*</span><span class="nx">t</span><span class="o">*</span><span class="nx">t</span><span class="o">*</span><span class="nx">t</span> <span class="o">+</span> <span class="nx">b</span><span class="p">;</span></div><div class='line' id='LC66'>		<span class="k">return</span> <span class="nx">c</span><span class="o">/</span><span class="mi">2</span><span class="o">*</span><span class="p">((</span><span class="nx">t</span><span class="o">-=</span><span class="mi">2</span><span class="p">)</span><span class="o">*</span><span class="nx">t</span><span class="o">*</span><span class="nx">t</span> <span class="o">+</span> <span class="mi">2</span><span class="p">)</span> <span class="o">+</span> <span class="nx">b</span><span class="p">;</span></div><div class='line' id='LC67'>	<span class="p">},</span></div><div class='line' id='LC68'>	<span class="nx">easeInQuart</span><span class="o">:</span> <span class="kd">function</span> <span class="p">(</span><span class="nx">x</span><span class="p">,</span> <span class="nx">t</span><span class="p">,</span> <span class="nx">b</span><span class="p">,</span> <span class="nx">c</span><span class="p">,</span> <span class="nx">d</span><span class="p">)</span> <span class="p">{</span></div><div class='line' id='LC69'>		<span class="k">return</span> <span class="nx">c</span><span class="o">*</span><span class="p">(</span><span class="nx">t</span><span class="o">/=</span><span class="nx">d</span><span class="p">)</span><span class="o">*</span><span class="nx">t</span><span class="o">*</span><span class="nx">t</span><span class="o">*</span><span class="nx">t</span> <span class="o">+</span> <span class="nx">b</span><span class="p">;</span></div><div class='line' id='LC70'>	<span class="p">},</span></div><div class='line' id='LC71'>	<span class="nx">easeOutQuart</span><span class="o">:</span> <span class="kd">function</span> <span class="p">(</span><span class="nx">x</span><span class="p">,</span> <span class="nx">t</span><span class="p">,</span> <span class="nx">b</span><span class="p">,</span> <span class="nx">c</span><span class="p">,</span> <span class="nx">d</span><span class="p">)</span> <span class="p">{</span></div><div class='line' id='LC72'>		<span class="k">return</span> <span class="o">-</span><span class="nx">c</span> <span class="o">*</span> <span class="p">((</span><span class="nx">t</span><span class="o">=</span><span class="nx">t</span><span class="o">/</span><span class="nx">d</span><span class="o">-</span><span class="mi">1</span><span class="p">)</span><span class="o">*</span><span class="nx">t</span><span class="o">*</span><span class="nx">t</span><span class="o">*</span><span class="nx">t</span> <span class="o">-</span> <span class="mi">1</span><span class="p">)</span> <span class="o">+</span> <span class="nx">b</span><span class="p">;</span></div><div class='line' id='LC73'>	<span class="p">},</span></div><div class='line' id='LC74'>	<span class="nx">easeInOutQuart</span><span class="o">:</span> <span class="kd">function</span> <span class="p">(</span><span class="nx">x</span><span class="p">,</span> <span class="nx">t</span><span class="p">,</span> <span class="nx">b</span><span class="p">,</span> <span class="nx">c</span><span class="p">,</span> <span class="nx">d</span><span class="p">)</span> <span class="p">{</span></div><div class='line' id='LC75'>		<span class="k">if</span> <span class="p">((</span><span class="nx">t</span><span class="o">/=</span><span class="nx">d</span><span class="o">/</span><span class="mi">2</span><span class="p">)</span> <span class="o">&lt;</span> <span class="mi">1</span><span class="p">)</span> <span class="k">return</span> <span class="nx">c</span><span class="o">/</span><span class="mi">2</span><span class="o">*</span><span class="nx">t</span><span class="o">*</span><span class="nx">t</span><span class="o">*</span><span class="nx">t</span><span class="o">*</span><span class="nx">t</span> <span class="o">+</span> <span class="nx">b</span><span class="p">;</span></div><div class='line' id='LC76'>		<span class="k">return</span> <span class="o">-</span><span class="nx">c</span><span class="o">/</span><span class="mi">2</span> <span class="o">*</span> <span class="p">((</span><span class="nx">t</span><span class="o">-=</span><span class="mi">2</span><span class="p">)</span><span class="o">*</span><span class="nx">t</span><span class="o">*</span><span class="nx">t</span><span class="o">*</span><span class="nx">t</span> <span class="o">-</span> <span class="mi">2</span><span class="p">)</span> <span class="o">+</span> <span class="nx">b</span><span class="p">;</span></div><div class='line' id='LC77'>	<span class="p">},</span></div><div class='line' id='LC78'>	<span class="nx">easeInQuint</span><span class="o">:</span> <span class="kd">function</span> <span class="p">(</span><span class="nx">x</span><span class="p">,</span> <span class="nx">t</span><span class="p">,</span> <span class="nx">b</span><span class="p">,</span> <span class="nx">c</span><span class="p">,</span> <span class="nx">d</span><span class="p">)</span> <span class="p">{</span></div><div class='line' id='LC79'>		<span class="k">return</span> <span class="nx">c</span><span class="o">*</span><span class="p">(</span><span class="nx">t</span><span class="o">/=</span><span class="nx">d</span><span class="p">)</span><span class="o">*</span><span class="nx">t</span><span class="o">*</span><span class="nx">t</span><span class="o">*</span><span class="nx">t</span><span class="o">*</span><span class="nx">t</span> <span class="o">+</span> <span class="nx">b</span><span class="p">;</span></div><div class='line' id='LC80'>	<span class="p">},</span></div><div class='line' id='LC81'>	<span class="nx">easeOutQuint</span><span class="o">:</span> <span class="kd">function</span> <span class="p">(</span><span class="nx">x</span><span class="p">,</span> <span class="nx">t</span><span class="p">,</span> <span class="nx">b</span><span class="p">,</span> <span class="nx">c</span><span class="p">,</span> <span class="nx">d</span><span class="p">)</span> <span class="p">{</span></div><div class='line' id='LC82'>		<span class="k">return</span> <span class="nx">c</span><span class="o">*</span><span class="p">((</span><span class="nx">t</span><span class="o">=</span><span class="nx">t</span><span class="o">/</span><span class="nx">d</span><span class="o">-</span><span class="mi">1</span><span class="p">)</span><span class="o">*</span><span class="nx">t</span><span class="o">*</span><span class="nx">t</span><span class="o">*</span><span class="nx">t</span><span class="o">*</span><span class="nx">t</span> <span class="o">+</span> <span class="mi">1</span><span class="p">)</span> <span class="o">+</span> <span class="nx">b</span><span class="p">;</span></div><div class='line' id='LC83'>	<span class="p">},</span></div><div class='line' id='LC84'>	<span class="nx">easeInOutQuint</span><span class="o">:</span> <span class="kd">function</span> <span class="p">(</span><span class="nx">x</span><span class="p">,</span> <span class="nx">t</span><span class="p">,</span> <span class="nx">b</span><span class="p">,</span> <span class="nx">c</span><span class="p">,</span> <span class="nx">d</span><span class="p">)</span> <span class="p">{</span></div><div class='line' id='LC85'>		<span class="k">if</span> <span class="p">((</span><span class="nx">t</span><span class="o">/=</span><span class="nx">d</span><span class="o">/</span><span class="mi">2</span><span class="p">)</span> <span class="o">&lt;</span> <span class="mi">1</span><span class="p">)</span> <span class="k">return</span> <span class="nx">c</span><span class="o">/</span><span class="mi">2</span><span class="o">*</span><span class="nx">t</span><span class="o">*</span><span class="nx">t</span><span class="o">*</span><span class="nx">t</span><span class="o">*</span><span class="nx">t</span><span class="o">*</span><span class="nx">t</span> <span class="o">+</span> <span class="nx">b</span><span class="p">;</span></div><div class='line' id='LC86'>		<span class="k">return</span> <span class="nx">c</span><span class="o">/</span><span class="mi">2</span><span class="o">*</span><span class="p">((</span><span class="nx">t</span><span class="o">-=</span><span class="mi">2</span><span class="p">)</span><span class="o">*</span><span class="nx">t</span><span class="o">*</span><span class="nx">t</span><span class="o">*</span><span class="nx">t</span><span class="o">*</span><span class="nx">t</span> <span class="o">+</span> <span class="mi">2</span><span class="p">)</span> <span class="o">+</span> <span class="nx">b</span><span class="p">;</span></div><div class='line' id='LC87'>	<span class="p">},</span></div><div class='line' id='LC88'>	<span class="nx">easeInSine</span><span class="o">:</span> <span class="kd">function</span> <span class="p">(</span><span class="nx">x</span><span class="p">,</span> <span class="nx">t</span><span class="p">,</span> <span class="nx">b</span><span class="p">,</span> <span class="nx">c</span><span class="p">,</span> <span class="nx">d</span><span class="p">)</span> <span class="p">{</span></div><div class='line' id='LC89'>		<span class="k">return</span> <span class="o">-</span><span class="nx">c</span> <span class="o">*</span> <span class="nb">Math</span><span class="p">.</span><span class="nx">cos</span><span class="p">(</span><span class="nx">t</span><span class="o">/</span><span class="nx">d</span> <span class="o">*</span> <span class="p">(</span><span class="nb">Math</span><span class="p">.</span><span class="nx">PI</span><span class="o">/</span><span class="mi">2</span><span class="p">))</span> <span class="o">+</span> <span class="nx">c</span> <span class="o">+</span> <span class="nx">b</span><span class="p">;</span></div><div class='line' id='LC90'>	<span class="p">},</span></div><div class='line' id='LC91'>	<span class="nx">easeOutSine</span><span class="o">:</span> <span class="kd">function</span> <span class="p">(</span><span class="nx">x</span><span class="p">,</span> <span class="nx">t</span><span class="p">,</span> <span class="nx">b</span><span class="p">,</span> <span class="nx">c</span><span class="p">,</span> <span class="nx">d</span><span class="p">)</span> <span class="p">{</span></div><div class='line' id='LC92'>		<span class="k">return</span> <span class="nx">c</span> <span class="o">*</span> <span class="nb">Math</span><span class="p">.</span><span class="nx">sin</span><span class="p">(</span><span class="nx">t</span><span class="o">/</span><span class="nx">d</span> <span class="o">*</span> <span class="p">(</span><span class="nb">Math</span><span class="p">.</span><span class="nx">PI</span><span class="o">/</span><span class="mi">2</span><span class="p">))</span> <span class="o">+</span> <span class="nx">b</span><span class="p">;</span></div><div class='line' id='LC93'>	<span class="p">},</span></div><div class='line' id='LC94'>	<span class="nx">easeInOutSine</span><span class="o">:</span> <span class="kd">function</span> <span class="p">(</span><span class="nx">x</span><span class="p">,</span> <span class="nx">t</span><span class="p">,</span> <span class="nx">b</span><span class="p">,</span> <span class="nx">c</span><span class="p">,</span> <span class="nx">d</span><span class="p">)</span> <span class="p">{</span></div><div class='line' id='LC95'>		<span class="k">return</span> <span class="o">-</span><span class="nx">c</span><span class="o">/</span><span class="mi">2</span> <span class="o">*</span> <span class="p">(</span><span class="nb">Math</span><span class="p">.</span><span class="nx">cos</span><span class="p">(</span><span class="nb">Math</span><span class="p">.</span><span class="nx">PI</span><span class="o">*</span><span class="nx">t</span><span class="o">/</span><span class="nx">d</span><span class="p">)</span> <span class="o">-</span> <span class="mi">1</span><span class="p">)</span> <span class="o">+</span> <span class="nx">b</span><span class="p">;</span></div><div class='line' id='LC96'>	<span class="p">},</span></div><div class='line' id='LC97'>	<span class="nx">easeInExpo</span><span class="o">:</span> <span class="kd">function</span> <span class="p">(</span><span class="nx">x</span><span class="p">,</span> <span class="nx">t</span><span class="p">,</span> <span class="nx">b</span><span class="p">,</span> <span class="nx">c</span><span class="p">,</span> <span class="nx">d</span><span class="p">)</span> <span class="p">{</span></div><div class='line' id='LC98'>		<span class="k">return</span> <span class="p">(</span><span class="nx">t</span><span class="o">==</span><span class="mi">0</span><span class="p">)</span> <span class="o">?</span> <span class="nx">b</span> <span class="o">:</span> <span class="nx">c</span> <span class="o">*</span> <span class="nb">Math</span><span class="p">.</span><span class="nx">pow</span><span class="p">(</span><span class="mi">2</span><span class="p">,</span> <span class="mi">10</span> <span class="o">*</span> <span class="p">(</span><span class="nx">t</span><span class="o">/</span><span class="nx">d</span> <span class="o">-</span> <span class="mi">1</span><span class="p">))</span> <span class="o">+</span> <span class="nx">b</span><span class="p">;</span></div><div class='line' id='LC99'>	<span class="p">},</span></div><div class='line' id='LC100'>	<span class="nx">easeOutExpo</span><span class="o">:</span> <span class="kd">function</span> <span class="p">(</span><span class="nx">x</span><span class="p">,</span> <span class="nx">t</span><span class="p">,</span> <span class="nx">b</span><span class="p">,</span> <span class="nx">c</span><span class="p">,</span> <span class="nx">d</span><span class="p">)</span> <span class="p">{</span></div><div class='line' id='LC101'>		<span class="k">return</span> <span class="p">(</span><span class="nx">t</span><span class="o">==</span><span class="nx">d</span><span class="p">)</span> <span class="o">?</span> <span class="nx">b</span><span class="o">+</span><span class="nx">c</span> <span class="o">:</span> <span class="nx">c</span> <span class="o">*</span> <span class="p">(</span><span class="o">-</span><span class="nb">Math</span><span class="p">.</span><span class="nx">pow</span><span class="p">(</span><span class="mi">2</span><span class="p">,</span> <span class="o">-</span><span class="mi">10</span> <span class="o">*</span> <span class="nx">t</span><span class="o">/</span><span class="nx">d</span><span class="p">)</span> <span class="o">+</span> <span class="mi">1</span><span class="p">)</span> <span class="o">+</span> <span class="nx">b</span><span class="p">;</span></div><div class='line' id='LC102'>	<span class="p">},</span></div><div class='line' id='LC103'>	<span class="nx">easeInOutExpo</span><span class="o">:</span> <span class="kd">function</span> <span class="p">(</span><span class="nx">x</span><span class="p">,</span> <span class="nx">t</span><span class="p">,</span> <span class="nx">b</span><span class="p">,</span> <span class="nx">c</span><span class="p">,</span> <span class="nx">d</span><span class="p">)</span> <span class="p">{</span></div><div class='line' id='LC104'>		<span class="k">if</span> <span class="p">(</span><span class="nx">t</span><span class="o">==</span><span class="mi">0</span><span class="p">)</span> <span class="k">return</span> <span class="nx">b</span><span class="p">;</span></div><div class='line' id='LC105'>		<span class="k">if</span> <span class="p">(</span><span class="nx">t</span><span class="o">==</span><span class="nx">d</span><span class="p">)</span> <span class="k">return</span> <span class="nx">b</span><span class="o">+</span><span class="nx">c</span><span class="p">;</span></div><div class='line' id='LC106'>		<span class="k">if</span> <span class="p">((</span><span class="nx">t</span><span class="o">/=</span><span class="nx">d</span><span class="o">/</span><span class="mi">2</span><span class="p">)</span> <span class="o">&lt;</span> <span class="mi">1</span><span class="p">)</span> <span class="k">return</span> <span class="nx">c</span><span class="o">/</span><span class="mi">2</span> <span class="o">*</span> <span class="nb">Math</span><span class="p">.</span><span class="nx">pow</span><span class="p">(</span><span class="mi">2</span><span class="p">,</span> <span class="mi">10</span> <span class="o">*</span> <span class="p">(</span><span class="nx">t</span> <span class="o">-</span> <span class="mi">1</span><span class="p">))</span> <span class="o">+</span> <span class="nx">b</span><span class="p">;</span></div><div class='line' id='LC107'>		<span class="k">return</span> <span class="nx">c</span><span class="o">/</span><span class="mi">2</span> <span class="o">*</span> <span class="p">(</span><span class="o">-</span><span class="nb">Math</span><span class="p">.</span><span class="nx">pow</span><span class="p">(</span><span class="mi">2</span><span class="p">,</span> <span class="o">-</span><span class="mi">10</span> <span class="o">*</span> <span class="o">--</span><span class="nx">t</span><span class="p">)</span> <span class="o">+</span> <span class="mi">2</span><span class="p">)</span> <span class="o">+</span> <span class="nx">b</span><span class="p">;</span></div><div class='line' id='LC108'>	<span class="p">},</span></div><div class='line' id='LC109'>	<span class="nx">easeInCirc</span><span class="o">:</span> <span class="kd">function</span> <span class="p">(</span><span class="nx">x</span><span class="p">,</span> <span class="nx">t</span><span class="p">,</span> <span class="nx">b</span><span class="p">,</span> <span class="nx">c</span><span class="p">,</span> <span class="nx">d</span><span class="p">)</span> <span class="p">{</span></div><div class='line' id='LC110'>		<span class="k">return</span> <span class="o">-</span><span class="nx">c</span> <span class="o">*</span> <span class="p">(</span><span class="nb">Math</span><span class="p">.</span><span class="nx">sqrt</span><span class="p">(</span><span class="mi">1</span> <span class="o">-</span> <span class="p">(</span><span class="nx">t</span><span class="o">/=</span><span class="nx">d</span><span class="p">)</span><span class="o">*</span><span class="nx">t</span><span class="p">)</span> <span class="o">-</span> <span class="mi">1</span><span class="p">)</span> <span class="o">+</span> <span class="nx">b</span><span class="p">;</span></div><div class='line' id='LC111'>	<span class="p">},</span></div><div class='line' id='LC112'>	<span class="nx">easeOutCirc</span><span class="o">:</span> <span class="kd">function</span> <span class="p">(</span><span class="nx">x</span><span class="p">,</span> <span class="nx">t</span><span class="p">,</span> <span class="nx">b</span><span class="p">,</span> <span class="nx">c</span><span class="p">,</span> <span class="nx">d</span><span class="p">)</span> <span class="p">{</span></div><div class='line' id='LC113'>		<span class="k">return</span> <span class="nx">c</span> <span class="o">*</span> <span class="nb">Math</span><span class="p">.</span><span class="nx">sqrt</span><span class="p">(</span><span class="mi">1</span> <span class="o">-</span> <span class="p">(</span><span class="nx">t</span><span class="o">=</span><span class="nx">t</span><span class="o">/</span><span class="nx">d</span><span class="o">-</span><span class="mi">1</span><span class="p">)</span><span class="o">*</span><span class="nx">t</span><span class="p">)</span> <span class="o">+</span> <span class="nx">b</span><span class="p">;</span></div><div class='line' id='LC114'>	<span class="p">},</span></div><div class='line' id='LC115'>	<span class="nx">easeInOutCirc</span><span class="o">:</span> <span class="kd">function</span> <span class="p">(</span><span class="nx">x</span><span class="p">,</span> <span class="nx">t</span><span class="p">,</span> <span class="nx">b</span><span class="p">,</span> <span class="nx">c</span><span class="p">,</span> <span class="nx">d</span><span class="p">)</span> <span class="p">{</span></div><div class='line' id='LC116'>		<span class="k">if</span> <span class="p">((</span><span class="nx">t</span><span class="o">/=</span><span class="nx">d</span><span class="o">/</span><span class="mi">2</span><span class="p">)</span> <span class="o">&lt;</span> <span class="mi">1</span><span class="p">)</span> <span class="k">return</span> <span class="o">-</span><span class="nx">c</span><span class="o">/</span><span class="mi">2</span> <span class="o">*</span> <span class="p">(</span><span class="nb">Math</span><span class="p">.</span><span class="nx">sqrt</span><span class="p">(</span><span class="mi">1</span> <span class="o">-</span> <span class="nx">t</span><span class="o">*</span><span class="nx">t</span><span class="p">)</span> <span class="o">-</span> <span class="mi">1</span><span class="p">)</span> <span class="o">+</span> <span class="nx">b</span><span class="p">;</span></div><div class='line' id='LC117'>		<span class="k">return</span> <span class="nx">c</span><span class="o">/</span><span class="mi">2</span> <span class="o">*</span> <span class="p">(</span><span class="nb">Math</span><span class="p">.</span><span class="nx">sqrt</span><span class="p">(</span><span class="mi">1</span> <span class="o">-</span> <span class="p">(</span><span class="nx">t</span><span class="o">-=</span><span class="mi">2</span><span class="p">)</span><span class="o">*</span><span class="nx">t</span><span class="p">)</span> <span class="o">+</span> <span class="mi">1</span><span class="p">)</span> <span class="o">+</span> <span class="nx">b</span><span class="p">;</span></div><div class='line' id='LC118'>	<span class="p">},</span></div><div class='line' id='LC119'>	<span class="nx">easeInElastic</span><span class="o">:</span> <span class="kd">function</span> <span class="p">(</span><span class="nx">x</span><span class="p">,</span> <span class="nx">t</span><span class="p">,</span> <span class="nx">b</span><span class="p">,</span> <span class="nx">c</span><span class="p">,</span> <span class="nx">d</span><span class="p">)</span> <span class="p">{</span></div><div class='line' id='LC120'>		<span class="kd">var</span> <span class="nx">s</span><span class="o">=</span><span class="mf">1.70158</span><span class="p">;</span><span class="kd">var</span> <span class="nx">p</span><span class="o">=</span><span class="mi">0</span><span class="p">;</span><span class="kd">var</span> <span class="nx">a</span><span class="o">=</span><span class="nx">c</span><span class="p">;</span></div><div class='line' id='LC121'>		<span class="k">if</span> <span class="p">(</span><span class="nx">t</span><span class="o">==</span><span class="mi">0</span><span class="p">)</span> <span class="k">return</span> <span class="nx">b</span><span class="p">;</span>  <span class="k">if</span> <span class="p">((</span><span class="nx">t</span><span class="o">/=</span><span class="nx">d</span><span class="p">)</span><span class="o">==</span><span class="mi">1</span><span class="p">)</span> <span class="k">return</span> <span class="nx">b</span><span class="o">+</span><span class="nx">c</span><span class="p">;</span>  <span class="k">if</span> <span class="p">(</span><span class="o">!</span><span class="nx">p</span><span class="p">)</span> <span class="nx">p</span><span class="o">=</span><span class="nx">d</span><span class="o">*</span><span class="p">.</span><span class="mi">3</span><span class="p">;</span></div><div class='line' id='LC122'>		<span class="k">if</span> <span class="p">(</span><span class="nx">a</span> <span class="o">&lt;</span> <span class="nb">Math</span><span class="p">.</span><span class="nx">abs</span><span class="p">(</span><span class="nx">c</span><span class="p">))</span> <span class="p">{</span> <span class="nx">a</span><span class="o">=</span><span class="nx">c</span><span class="p">;</span> <span class="kd">var</span> <span class="nx">s</span><span class="o">=</span><span class="nx">p</span><span class="o">/</span><span class="mi">4</span><span class="p">;</span> <span class="p">}</span></div><div class='line' id='LC123'>		<span class="k">else</span> <span class="kd">var</span> <span class="nx">s</span> <span class="o">=</span> <span class="nx">p</span><span class="o">/</span><span class="p">(</span><span class="mi">2</span><span class="o">*</span><span class="nb">Math</span><span class="p">.</span><span class="nx">PI</span><span class="p">)</span> <span class="o">*</span> <span class="nb">Math</span><span class="p">.</span><span class="nx">asin</span> <span class="p">(</span><span class="nx">c</span><span class="o">/</span><span class="nx">a</span><span class="p">);</span></div><div class='line' id='LC124'>		<span class="k">return</span> <span class="o">-</span><span class="p">(</span><span class="nx">a</span><span class="o">*</span><span class="nb">Math</span><span class="p">.</span><span class="nx">pow</span><span class="p">(</span><span class="mi">2</span><span class="p">,</span><span class="mi">10</span><span class="o">*</span><span class="p">(</span><span class="nx">t</span><span class="o">-=</span><span class="mi">1</span><span class="p">))</span> <span class="o">*</span> <span class="nb">Math</span><span class="p">.</span><span class="nx">sin</span><span class="p">(</span> <span class="p">(</span><span class="nx">t</span><span class="o">*</span><span class="nx">d</span><span class="o">-</span><span class="nx">s</span><span class="p">)</span><span class="o">*</span><span class="p">(</span><span class="mi">2</span><span class="o">*</span><span class="nb">Math</span><span class="p">.</span><span class="nx">PI</span><span class="p">)</span><span class="o">/</span><span class="nx">p</span> <span class="p">))</span> <span class="o">+</span> <span class="nx">b</span><span class="p">;</span></div><div class='line' id='LC125'>	<span class="p">},</span></div><div class='line' id='LC126'>	<span class="nx">easeOutElastic</span><span class="o">:</span> <span class="kd">function</span> <span class="p">(</span><span class="nx">x</span><span class="p">,</span> <span class="nx">t</span><span class="p">,</span> <span class="nx">b</span><span class="p">,</span> <span class="nx">c</span><span class="p">,</span> <span class="nx">d</span><span class="p">)</span> <span class="p">{</span></div><div class='line' id='LC127'>		<span class="kd">var</span> <span class="nx">s</span><span class="o">=</span><span class="mf">1.70158</span><span class="p">;</span><span class="kd">var</span> <span class="nx">p</span><span class="o">=</span><span class="mi">0</span><span class="p">;</span><span class="kd">var</span> <span class="nx">a</span><span class="o">=</span><span class="nx">c</span><span class="p">;</span></div><div class='line' id='LC128'>		<span class="k">if</span> <span class="p">(</span><span class="nx">t</span><span class="o">==</span><span class="mi">0</span><span class="p">)</span> <span class="k">return</span> <span class="nx">b</span><span class="p">;</span>  <span class="k">if</span> <span class="p">((</span><span class="nx">t</span><span class="o">/=</span><span class="nx">d</span><span class="p">)</span><span class="o">==</span><span class="mi">1</span><span class="p">)</span> <span class="k">return</span> <span class="nx">b</span><span class="o">+</span><span class="nx">c</span><span class="p">;</span>  <span class="k">if</span> <span class="p">(</span><span class="o">!</span><span class="nx">p</span><span class="p">)</span> <span class="nx">p</span><span class="o">=</span><span class="nx">d</span><span class="o">*</span><span class="p">.</span><span class="mi">3</span><span class="p">;</span></div><div class='line' id='LC129'>		<span class="k">if</span> <span class="p">(</span><span class="nx">a</span> <span class="o">&lt;</span> <span class="nb">Math</span><span class="p">.</span><span class="nx">abs</span><span class="p">(</span><span class="nx">c</span><span class="p">))</span> <span class="p">{</span> <span class="nx">a</span><span class="o">=</span><span class="nx">c</span><span class="p">;</span> <span class="kd">var</span> <span class="nx">s</span><span class="o">=</span><span class="nx">p</span><span class="o">/</span><span class="mi">4</span><span class="p">;</span> <span class="p">}</span></div><div class='line' id='LC130'>		<span class="k">else</span> <span class="kd">var</span> <span class="nx">s</span> <span class="o">=</span> <span class="nx">p</span><span class="o">/</span><span class="p">(</span><span class="mi">2</span><span class="o">*</span><span class="nb">Math</span><span class="p">.</span><span class="nx">PI</span><span class="p">)</span> <span class="o">*</span> <span class="nb">Math</span><span class="p">.</span><span class="nx">asin</span> <span class="p">(</span><span class="nx">c</span><span class="o">/</span><span class="nx">a</span><span class="p">);</span></div><div class='line' id='LC131'>		<span class="k">return</span> <span class="nx">a</span><span class="o">*</span><span class="nb">Math</span><span class="p">.</span><span class="nx">pow</span><span class="p">(</span><span class="mi">2</span><span class="p">,</span><span class="o">-</span><span class="mi">10</span><span class="o">*</span><span class="nx">t</span><span class="p">)</span> <span class="o">*</span> <span class="nb">Math</span><span class="p">.</span><span class="nx">sin</span><span class="p">(</span> <span class="p">(</span><span class="nx">t</span><span class="o">*</span><span class="nx">d</span><span class="o">-</span><span class="nx">s</span><span class="p">)</span><span class="o">*</span><span class="p">(</span><span class="mi">2</span><span class="o">*</span><span class="nb">Math</span><span class="p">.</span><span class="nx">PI</span><span class="p">)</span><span class="o">/</span><span class="nx">p</span> <span class="p">)</span> <span class="o">+</span> <span class="nx">c</span> <span class="o">+</span> <span class="nx">b</span><span class="p">;</span></div><div class='line' id='LC132'>	<span class="p">},</span></div><div class='line' id='LC133'>	<span class="nx">easeInOutElastic</span><span class="o">:</span> <span class="kd">function</span> <span class="p">(</span><span class="nx">x</span><span class="p">,</span> <span class="nx">t</span><span class="p">,</span> <span class="nx">b</span><span class="p">,</span> <span class="nx">c</span><span class="p">,</span> <span class="nx">d</span><span class="p">)</span> <span class="p">{</span></div><div class='line' id='LC134'>		<span class="kd">var</span> <span class="nx">s</span><span class="o">=</span><span class="mf">1.70158</span><span class="p">;</span><span class="kd">var</span> <span class="nx">p</span><span class="o">=</span><span class="mi">0</span><span class="p">;</span><span class="kd">var</span> <span class="nx">a</span><span class="o">=</span><span class="nx">c</span><span class="p">;</span></div><div class='line' id='LC135'>		<span class="k">if</span> <span class="p">(</span><span class="nx">t</span><span class="o">==</span><span class="mi">0</span><span class="p">)</span> <span class="k">return</span> <span class="nx">b</span><span class="p">;</span>  <span class="k">if</span> <span class="p">((</span><span class="nx">t</span><span class="o">/=</span><span class="nx">d</span><span class="o">/</span><span class="mi">2</span><span class="p">)</span><span class="o">==</span><span class="mi">2</span><span class="p">)</span> <span class="k">return</span> <span class="nx">b</span><span class="o">+</span><span class="nx">c</span><span class="p">;</span>  <span class="k">if</span> <span class="p">(</span><span class="o">!</span><span class="nx">p</span><span class="p">)</span> <span class="nx">p</span><span class="o">=</span><span class="nx">d</span><span class="o">*</span><span class="p">(.</span><span class="mi">3</span><span class="o">*</span><span class="mf">1.5</span><span class="p">);</span></div><div class='line' id='LC136'>		<span class="k">if</span> <span class="p">(</span><span class="nx">a</span> <span class="o">&lt;</span> <span class="nb">Math</span><span class="p">.</span><span class="nx">abs</span><span class="p">(</span><span class="nx">c</span><span class="p">))</span> <span class="p">{</span> <span class="nx">a</span><span class="o">=</span><span class="nx">c</span><span class="p">;</span> <span class="kd">var</span> <span class="nx">s</span><span class="o">=</span><span class="nx">p</span><span class="o">/</span><span class="mi">4</span><span class="p">;</span> <span class="p">}</span></div><div class='line' id='LC137'>		<span class="k">else</span> <span class="kd">var</span> <span class="nx">s</span> <span class="o">=</span> <span class="nx">p</span><span class="o">/</span><span class="p">(</span><span class="mi">2</span><span class="o">*</span><span class="nb">Math</span><span class="p">.</span><span class="nx">PI</span><span class="p">)</span> <span class="o">*</span> <span class="nb">Math</span><span class="p">.</span><span class="nx">asin</span> <span class="p">(</span><span class="nx">c</span><span class="o">/</span><span class="nx">a</span><span class="p">);</span></div><div class='line' id='LC138'>		<span class="k">if</span> <span class="p">(</span><span class="nx">t</span> <span class="o">&lt;</span> <span class="mi">1</span><span class="p">)</span> <span class="k">return</span> <span class="o">-</span><span class="p">.</span><span class="mi">5</span><span class="o">*</span><span class="p">(</span><span class="nx">a</span><span class="o">*</span><span class="nb">Math</span><span class="p">.</span><span class="nx">pow</span><span class="p">(</span><span class="mi">2</span><span class="p">,</span><span class="mi">10</span><span class="o">*</span><span class="p">(</span><span class="nx">t</span><span class="o">-=</span><span class="mi">1</span><span class="p">))</span> <span class="o">*</span> <span class="nb">Math</span><span class="p">.</span><span class="nx">sin</span><span class="p">(</span> <span class="p">(</span><span class="nx">t</span><span class="o">*</span><span class="nx">d</span><span class="o">-</span><span class="nx">s</span><span class="p">)</span><span class="o">*</span><span class="p">(</span><span class="mi">2</span><span class="o">*</span><span class="nb">Math</span><span class="p">.</span><span class="nx">PI</span><span class="p">)</span><span class="o">/</span><span class="nx">p</span> <span class="p">))</span> <span class="o">+</span> <span class="nx">b</span><span class="p">;</span></div><div class='line' id='LC139'>		<span class="k">return</span> <span class="nx">a</span><span class="o">*</span><span class="nb">Math</span><span class="p">.</span><span class="nx">pow</span><span class="p">(</span><span class="mi">2</span><span class="p">,</span><span class="o">-</span><span class="mi">10</span><span class="o">*</span><span class="p">(</span><span class="nx">t</span><span class="o">-=</span><span class="mi">1</span><span class="p">))</span> <span class="o">*</span> <span class="nb">Math</span><span class="p">.</span><span class="nx">sin</span><span class="p">(</span> <span class="p">(</span><span class="nx">t</span><span class="o">*</span><span class="nx">d</span><span class="o">-</span><span class="nx">s</span><span class="p">)</span><span class="o">*</span><span class="p">(</span><span class="mi">2</span><span class="o">*</span><span class="nb">Math</span><span class="p">.</span><span class="nx">PI</span><span class="p">)</span><span class="o">/</span><span class="nx">p</span> <span class="p">)</span><span class="o">*</span><span class="p">.</span><span class="mi">5</span> <span class="o">+</span> <span class="nx">c</span> <span class="o">+</span> <span class="nx">b</span><span class="p">;</span></div><div class='line' id='LC140'>	<span class="p">},</span></div><div class='line' id='LC141'>	<span class="nx">easeInBack</span><span class="o">:</span> <span class="kd">function</span> <span class="p">(</span><span class="nx">x</span><span class="p">,</span> <span class="nx">t</span><span class="p">,</span> <span class="nx">b</span><span class="p">,</span> <span class="nx">c</span><span class="p">,</span> <span class="nx">d</span><span class="p">,</span> <span class="nx">s</span><span class="p">)</span> <span class="p">{</span></div><div class='line' id='LC142'>		<span class="k">if</span> <span class="p">(</span><span class="nx">s</span> <span class="o">==</span> <span class="kc">undefined</span><span class="p">)</span> <span class="nx">s</span> <span class="o">=</span> <span class="mf">1.70158</span><span class="p">;</span></div><div class='line' id='LC143'>		<span class="k">return</span> <span class="nx">c</span><span class="o">*</span><span class="p">(</span><span class="nx">t</span><span class="o">/=</span><span class="nx">d</span><span class="p">)</span><span class="o">*</span><span class="nx">t</span><span class="o">*</span><span class="p">((</span><span class="nx">s</span><span class="o">+</span><span class="mi">1</span><span class="p">)</span><span class="o">*</span><span class="nx">t</span> <span class="o">-</span> <span class="nx">s</span><span class="p">)</span> <span class="o">+</span> <span class="nx">b</span><span class="p">;</span></div><div class='line' id='LC144'>	<span class="p">},</span></div><div class='line' id='LC145'>	<span class="nx">easeOutBack</span><span class="o">:</span> <span class="kd">function</span> <span class="p">(</span><span class="nx">x</span><span class="p">,</span> <span class="nx">t</span><span class="p">,</span> <span class="nx">b</span><span class="p">,</span> <span class="nx">c</span><span class="p">,</span> <span class="nx">d</span><span class="p">,</span> <span class="nx">s</span><span class="p">)</span> <span class="p">{</span></div><div class='line' id='LC146'>		<span class="k">if</span> <span class="p">(</span><span class="nx">s</span> <span class="o">==</span> <span class="kc">undefined</span><span class="p">)</span> <span class="nx">s</span> <span class="o">=</span> <span class="mf">1.70158</span><span class="p">;</span></div><div class='line' id='LC147'>		<span class="k">return</span> <span class="nx">c</span><span class="o">*</span><span class="p">((</span><span class="nx">t</span><span class="o">=</span><span class="nx">t</span><span class="o">/</span><span class="nx">d</span><span class="o">-</span><span class="mi">1</span><span class="p">)</span><span class="o">*</span><span class="nx">t</span><span class="o">*</span><span class="p">((</span><span class="nx">s</span><span class="o">+</span><span class="mi">1</span><span class="p">)</span><span class="o">*</span><span class="nx">t</span> <span class="o">+</span> <span class="nx">s</span><span class="p">)</span> <span class="o">+</span> <span class="mi">1</span><span class="p">)</span> <span class="o">+</span> <span class="nx">b</span><span class="p">;</span></div><div class='line' id='LC148'>	<span class="p">},</span></div><div class='line' id='LC149'>	<span class="nx">easeInOutBack</span><span class="o">:</span> <span class="kd">function</span> <span class="p">(</span><span class="nx">x</span><span class="p">,</span> <span class="nx">t</span><span class="p">,</span> <span class="nx">b</span><span class="p">,</span> <span class="nx">c</span><span class="p">,</span> <span class="nx">d</span><span class="p">,</span> <span class="nx">s</span><span class="p">)</span> <span class="p">{</span></div><div class='line' id='LC150'>		<span class="k">if</span> <span class="p">(</span><span class="nx">s</span> <span class="o">==</span> <span class="kc">undefined</span><span class="p">)</span> <span class="nx">s</span> <span class="o">=</span> <span class="mf">1.70158</span><span class="p">;</span> </div><div class='line' id='LC151'>		<span class="k">if</span> <span class="p">((</span><span class="nx">t</span><span class="o">/=</span><span class="nx">d</span><span class="o">/</span><span class="mi">2</span><span class="p">)</span> <span class="o">&lt;</span> <span class="mi">1</span><span class="p">)</span> <span class="k">return</span> <span class="nx">c</span><span class="o">/</span><span class="mi">2</span><span class="o">*</span><span class="p">(</span><span class="nx">t</span><span class="o">*</span><span class="nx">t</span><span class="o">*</span><span class="p">(((</span><span class="nx">s</span><span class="o">*=</span><span class="p">(</span><span class="mf">1.525</span><span class="p">))</span><span class="o">+</span><span class="mi">1</span><span class="p">)</span><span class="o">*</span><span class="nx">t</span> <span class="o">-</span> <span class="nx">s</span><span class="p">))</span> <span class="o">+</span> <span class="nx">b</span><span class="p">;</span></div><div class='line' id='LC152'>		<span class="k">return</span> <span class="nx">c</span><span class="o">/</span><span class="mi">2</span><span class="o">*</span><span class="p">((</span><span class="nx">t</span><span class="o">-=</span><span class="mi">2</span><span class="p">)</span><span class="o">*</span><span class="nx">t</span><span class="o">*</span><span class="p">(((</span><span class="nx">s</span><span class="o">*=</span><span class="p">(</span><span class="mf">1.525</span><span class="p">))</span><span class="o">+</span><span class="mi">1</span><span class="p">)</span><span class="o">*</span><span class="nx">t</span> <span class="o">+</span> <span class="nx">s</span><span class="p">)</span> <span class="o">+</span> <span class="mi">2</span><span class="p">)</span> <span class="o">+</span> <span class="nx">b</span><span class="p">;</span></div><div class='line' id='LC153'>	<span class="p">},</span></div><div class='line' id='LC154'>	<span class="nx">easeInBounce</span><span class="o">:</span> <span class="kd">function</span> <span class="p">(</span><span class="nx">x</span><span class="p">,</span> <span class="nx">t</span><span class="p">,</span> <span class="nx">b</span><span class="p">,</span> <span class="nx">c</span><span class="p">,</span> <span class="nx">d</span><span class="p">)</span> <span class="p">{</span></div><div class='line' id='LC155'>		<span class="k">return</span> <span class="nx">c</span> <span class="o">-</span> <span class="nx">jQuery</span><span class="p">.</span><span class="nx">easing</span><span class="p">.</span><span class="nx">easeOutBounce</span> <span class="p">(</span><span class="nx">x</span><span class="p">,</span> <span class="nx">d</span><span class="o">-</span><span class="nx">t</span><span class="p">,</span> <span class="mi">0</span><span class="p">,</span> <span class="nx">c</span><span class="p">,</span> <span class="nx">d</span><span class="p">)</span> <span class="o">+</span> <span class="nx">b</span><span class="p">;</span></div><div class='line' id='LC156'>	<span class="p">},</span></div><div class='line' id='LC157'>	<span class="nx">easeOutBounce</span><span class="o">:</span> <span class="kd">function</span> <span class="p">(</span><span class="nx">x</span><span class="p">,</span> <span class="nx">t</span><span class="p">,</span> <span class="nx">b</span><span class="p">,</span> <span class="nx">c</span><span class="p">,</span> <span class="nx">d</span><span class="p">)</span> <span class="p">{</span></div><div class='line' id='LC158'>		<span class="k">if</span> <span class="p">((</span><span class="nx">t</span><span class="o">/=</span><span class="nx">d</span><span class="p">)</span> <span class="o">&lt;</span> <span class="p">(</span><span class="mi">1</span><span class="o">/</span><span class="mf">2.75</span><span class="p">))</span> <span class="p">{</span></div><div class='line' id='LC159'>			<span class="k">return</span> <span class="nx">c</span><span class="o">*</span><span class="p">(</span><span class="mf">7.5625</span><span class="o">*</span><span class="nx">t</span><span class="o">*</span><span class="nx">t</span><span class="p">)</span> <span class="o">+</span> <span class="nx">b</span><span class="p">;</span></div><div class='line' id='LC160'>		<span class="p">}</span> <span class="k">else</span> <span class="k">if</span> <span class="p">(</span><span class="nx">t</span> <span class="o">&lt;</span> <span class="p">(</span><span class="mi">2</span><span class="o">/</span><span class="mf">2.75</span><span class="p">))</span> <span class="p">{</span></div><div class='line' id='LC161'>			<span class="k">return</span> <span class="nx">c</span><span class="o">*</span><span class="p">(</span><span class="mf">7.5625</span><span class="o">*</span><span class="p">(</span><span class="nx">t</span><span class="o">-=</span><span class="p">(</span><span class="mf">1.5</span><span class="o">/</span><span class="mf">2.75</span><span class="p">))</span><span class="o">*</span><span class="nx">t</span> <span class="o">+</span> <span class="p">.</span><span class="mi">75</span><span class="p">)</span> <span class="o">+</span> <span class="nx">b</span><span class="p">;</span></div><div class='line' id='LC162'>		<span class="p">}</span> <span class="k">else</span> <span class="k">if</span> <span class="p">(</span><span class="nx">t</span> <span class="o">&lt;</span> <span class="p">(</span><span class="mf">2.5</span><span class="o">/</span><span class="mf">2.75</span><span class="p">))</span> <span class="p">{</span></div><div class='line' id='LC163'>			<span class="k">return</span> <span class="nx">c</span><span class="o">*</span><span class="p">(</span><span class="mf">7.5625</span><span class="o">*</span><span class="p">(</span><span class="nx">t</span><span class="o">-=</span><span class="p">(</span><span class="mf">2.25</span><span class="o">/</span><span class="mf">2.75</span><span class="p">))</span><span class="o">*</span><span class="nx">t</span> <span class="o">+</span> <span class="p">.</span><span class="mi">9375</span><span class="p">)</span> <span class="o">+</span> <span class="nx">b</span><span class="p">;</span></div><div class='line' id='LC164'>		<span class="p">}</span> <span class="k">else</span> <span class="p">{</span></div><div class='line' id='LC165'>			<span class="k">return</span> <span class="nx">c</span><span class="o">*</span><span class="p">(</span><span class="mf">7.5625</span><span class="o">*</span><span class="p">(</span><span class="nx">t</span><span class="o">-=</span><span class="p">(</span><span class="mf">2.625</span><span class="o">/</span><span class="mf">2.75</span><span class="p">))</span><span class="o">*</span><span class="nx">t</span> <span class="o">+</span> <span class="p">.</span><span class="mi">984375</span><span class="p">)</span> <span class="o">+</span> <span class="nx">b</span><span class="p">;</span></div><div class='line' id='LC166'>		<span class="p">}</span></div><div class='line' id='LC167'>	<span class="p">},</span></div><div class='line' id='LC168'>	<span class="nx">easeInOutBounce</span><span class="o">:</span> <span class="kd">function</span> <span class="p">(</span><span class="nx">x</span><span class="p">,</span> <span class="nx">t</span><span class="p">,</span> <span class="nx">b</span><span class="p">,</span> <span class="nx">c</span><span class="p">,</span> <span class="nx">d</span><span class="p">)</span> <span class="p">{</span></div><div class='line' id='LC169'>		<span class="k">if</span> <span class="p">(</span><span class="nx">t</span> <span class="o">&lt;</span> <span class="nx">d</span><span class="o">/</span><span class="mi">2</span><span class="p">)</span> <span class="k">return</span> <span class="nx">jQuery</span><span class="p">.</span><span class="nx">easing</span><span class="p">.</span><span class="nx">easeInBounce</span> <span class="p">(</span><span class="nx">x</span><span class="p">,</span> <span class="nx">t</span><span class="o">*</span><span class="mi">2</span><span class="p">,</span> <span class="mi">0</span><span class="p">,</span> <span class="nx">c</span><span class="p">,</span> <span class="nx">d</span><span class="p">)</span> <span class="o">*</span> <span class="p">.</span><span class="mi">5</span> <span class="o">+</span> <span class="nx">b</span><span class="p">;</span></div><div class='line' id='LC170'>		<span class="k">return</span> <span class="nx">jQuery</span><span class="p">.</span><span class="nx">easing</span><span class="p">.</span><span class="nx">easeOutBounce</span> <span class="p">(</span><span class="nx">x</span><span class="p">,</span> <span class="nx">t</span><span class="o">*</span><span class="mi">2</span><span class="o">-</span><span class="nx">d</span><span class="p">,</span> <span class="mi">0</span><span class="p">,</span> <span class="nx">c</span><span class="p">,</span> <span class="nx">d</span><span class="p">)</span> <span class="o">*</span> <span class="p">.</span><span class="mi">5</span> <span class="o">+</span> <span class="nx">c</span><span class="o">*</span><span class="p">.</span><span class="mi">5</span> <span class="o">+</span> <span class="nx">b</span><span class="p">;</span></div><div class='line' id='LC171'>	<span class="p">}</span></div><div class='line' id='LC172'><span class="p">});</span></div><div class='line' id='LC173'><br/></div><div class='line' id='LC174'><span class="cm">/*</span></div><div class='line' id='LC175'><span class="cm"> *</span></div><div class='line' id='LC176'><span class="cm"> * TERMS OF USE - EASING EQUATIONS</span></div><div class='line' id='LC177'><span class="cm"> * </span></div><div class='line' id='LC178'><span class="cm"> * Open source under the BSD License. </span></div><div class='line' id='LC179'><span class="cm"> * </span></div><div class='line' id='LC180'><span class="cm"> * Copyright © 2001 Robert Penner</span></div><div class='line' id='LC181'><span class="cm"> * All rights reserved.</span></div><div class='line' id='LC182'><span class="cm"> * </span></div><div class='line' id='LC183'><span class="cm"> * Redistribution and use in source and binary forms, with or without modification, </span></div><div class='line' id='LC184'><span class="cm"> * are permitted provided that the following conditions are met:</span></div><div class='line' id='LC185'><span class="cm"> * </span></div><div class='line' id='LC186'><span class="cm"> * Redistributions of source code must retain the above copyright notice, this list of </span></div><div class='line' id='LC187'><span class="cm"> * conditions and the following disclaimer.</span></div><div class='line' id='LC188'><span class="cm"> * Redistributions in binary form must reproduce the above copyright notice, this list </span></div><div class='line' id='LC189'><span class="cm"> * of conditions and the following disclaimer in the documentation and/or other materials </span></div><div class='line' id='LC190'><span class="cm"> * provided with the distribution.</span></div><div class='line' id='LC191'><span class="cm"> * </span></div><div class='line' id='LC192'><span class="cm"> * Neither the name of the author nor the names of contributors may be used to endorse </span></div><div class='line' id='LC193'><span class="cm"> * or promote products derived from this software without specific prior written permission.</span></div><div class='line' id='LC194'><span class="cm"> * </span></div><div class='line' id='LC195'><span class="cm"> * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS &quot;AS IS&quot; AND ANY </span></div><div class='line' id='LC196'><span class="cm"> * EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF</span></div><div class='line' id='LC197'><span class="cm"> * MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE</span></div><div class='line' id='LC198'><span class="cm"> *  COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,</span></div><div class='line' id='LC199'><span class="cm"> *  EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE</span></div><div class='line' id='LC200'><span class="cm"> *  GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED </span></div><div class='line' id='LC201'><span class="cm"> * AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING</span></div><div class='line' id='LC202'><span class="cm"> *  NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED </span></div><div class='line' id='LC203'><span class="cm"> * OF THE POSSIBILITY OF SUCH DAMAGE. </span></div><div class='line' id='LC204'><span class="cm"> *</span></div><div class='line' id='LC205'><span class="cm"> */</span></div></pre></div>
          </td>
        </tr>
      </table>
  </div>

          </div>
        </div>
      </div>
    </div>

  </div>

<div class="frame frame-loading large-loading-area" style="display:none;" data-tree-list-url="/jquery/jquery-mobile/tree-list/33ddcd5960ab121f891db030d44013ce5fe6b02f" data-blob-url-prefix="/jquery/jquery-mobile/blob/33ddcd5960ab121f891db030d44013ce5fe6b02f">
  <img src="https://a248.e.akamai.net/assets.github.com/images/spinners/octocat-spinner-64.gif?1329872007" height="64" width="64">
</div>

      </div>
      <div class="context-overlay"></div>
    </div>

      <div id="footer-push"></div><!-- hack for sticky footer -->
    </div><!-- end of wrapper - hack for sticky footer -->

      <!-- footer -->
      <div id="footer" >
        
  <div class="upper_footer">
     <div class="container clearfix">

       <!--[if IE]><h4 id="blacktocat_ie">GitHub Links</h4><![endif]-->
       <![if !IE]><h4 id="blacktocat">GitHub Links</h4><![endif]>

       <ul class="footer_nav">
         <h4>GitHub</h4>
         <li><a href="https://github.com/about">About</a></li>
         <li><a href="https://github.com/blog">Blog</a></li>
         <li><a href="https://github.com/features">Features</a></li>
         <li><a href="https://github.com/contact">Contact &amp; Support</a></li>
         <li><a href="https://github.com/training">Training</a></li>
         <li><a href="http://enterprise.github.com/">GitHub Enterprise</a></li>
         <li><a href="http://status.github.com/">Site Status</a></li>
       </ul>

       <ul class="footer_nav">
         <h4>Tools</h4>
         <li><a href="http://get.gaug.es/">Gauges: Analyze web traffic</a></li>
         <li><a href="http://speakerdeck.com">Speaker Deck: Presentations</a></li>
         <li><a href="https://gist.github.com">Gist: Code snippets</a></li>
         <li><a href="http://mac.github.com/">GitHub for Mac</a></li>
         <li><a href="http://windows.github.com/">GitHub for Windows</a></li>
         <li><a href="http://mobile.github.com/">Issues for iPhone</a></li>
         <li><a href="http://jobs.github.com/">Job Board</a></li>
       </ul>

       <ul class="footer_nav">
         <h4>Extras</h4>
         <li><a href="http://shop.github.com/">GitHub Shop</a></li>
         <li><a href="http://octodex.github.com/">The Octodex</a></li>
       </ul>

       <ul class="footer_nav">
         <h4>Documentation</h4>
         <li><a href="http://help.github.com/">GitHub Help</a></li>
         <li><a href="http://developer.github.com/">Developer API</a></li>
         <li><a href="http://github.github.com/github-flavored-markdown/">GitHub Flavored Markdown</a></li>
         <li><a href="http://pages.github.com/">GitHub Pages</a></li>
       </ul>

     </div><!-- /.site -->
  </div><!-- /.upper_footer -->

<div class="lower_footer">
  <div class="container clearfix">
    <!--[if IE]><div id="legal_ie"><![endif]-->
    <![if !IE]><div id="legal"><![endif]>
      <ul>
          <li><a href="https://github.com/site/terms">Terms of Service</a></li>
          <li><a href="https://github.com/site/privacy">Privacy</a></li>
          <li><a href="https://github.com/security">Security</a></li>
      </ul>

      <p>&copy; 2012 <span title="0.30057s from fe9.rs.github.com">GitHub</span> Inc. All rights reserved.</p>
    </div><!-- /#legal or /#legal_ie-->

      <div class="sponsor">
        <a href="http://www.rackspace.com" class="logo">
          <img alt="Dedicated Server" height="36" src="https://a248.e.akamai.net/assets.github.com/images/modules/footer/rackspaces_logo.png?1329521039" width="38" />
        </a>
        Powered by the <a href="http://www.rackspace.com ">Dedicated
        Servers</a> and<br/> <a href="http://www.rackspacecloud.com">Cloud
        Computing</a> of Rackspace Hosting<span>&reg;</span>
      </div>
  </div><!-- /.site -->
</div><!-- /.lower_footer -->

      </div><!-- /#footer -->

    

<div id="keyboard_shortcuts_pane" class="instapaper_ignore readability-extra" style="display:none">
  <h2>Keyboard Shortcuts <small><a href="#" class="js-see-all-keyboard-shortcuts">(see all)</a></small></h2>

  <div class="columns threecols">
    <div class="column first">
      <h3>Site wide shortcuts</h3>
      <dl class="keyboard-mappings">
        <dt>s</dt>
        <dd>Focus site search</dd>
      </dl>
      <dl class="keyboard-mappings">
        <dt>?</dt>
        <dd>Bring up this help dialog</dd>
      </dl>
    </div><!-- /.column.first -->

    <div class="column middle" style='display:none'>
      <h3>Commit list</h3>
      <dl class="keyboard-mappings">
        <dt>j</dt>
        <dd>Move selection down</dd>
      </dl>
      <dl class="keyboard-mappings">
        <dt>k</dt>
        <dd>Move selection up</dd>
      </dl>
      <dl class="keyboard-mappings">
        <dt>c <em>or</em> o <em>or</em> enter</dt>
        <dd>Open commit</dd>
      </dl>
      <dl class="keyboard-mappings">
        <dt>y</dt>
        <dd>Expand URL to its canonical form</dd>
      </dl>
    </div><!-- /.column.first -->

    <div class="column last" style='display:none'>
      <h3>Pull request list</h3>
      <dl class="keyboard-mappings">
        <dt>j</dt>
        <dd>Move selection down</dd>
      </dl>
      <dl class="keyboard-mappings">
        <dt>k</dt>
        <dd>Move selection up</dd>
      </dl>
      <dl class="keyboard-mappings">
        <dt>o <em>or</em> enter</dt>
        <dd>Open issue</dd>
      </dl>
      <dl class="keyboard-mappings">
        <dt><span class="platform-mac">⌘</span><span class="platform-other">ctrl</span> <em>+</em> enter</dt>
        <dd>Submit comment</dd>
      </dl>
    </div><!-- /.columns.last -->

  </div><!-- /.columns.equacols -->

  <div style='display:none'>
    <div class="rule"></div>

    <h3>Issues</h3>

    <div class="columns threecols">
      <div class="column first">
        <dl class="keyboard-mappings">
          <dt>j</dt>
          <dd>Move selection down</dd>
        </dl>
        <dl class="keyboard-mappings">
          <dt>k</dt>
          <dd>Move selection up</dd>
        </dl>
        <dl class="keyboard-mappings">
          <dt>x</dt>
          <dd>Toggle selection</dd>
        </dl>
        <dl class="keyboard-mappings">
          <dt>o <em>or</em> enter</dt>
          <dd>Open issue</dd>
        </dl>
        <dl class="keyboard-mappings">
          <dt><span class="platform-mac">⌘</span><span class="platform-other">ctrl</span> <em>+</em> enter</dt>
          <dd>Submit comment</dd>
        </dl>
      </div><!-- /.column.first -->
      <div class="column last">
        <dl class="keyboard-mappings">
          <dt>c</dt>
          <dd>Create issue</dd>
        </dl>
        <dl class="keyboard-mappings">
          <dt>l</dt>
          <dd>Create label</dd>
        </dl>
        <dl class="keyboard-mappings">
          <dt>i</dt>
          <dd>Back to inbox</dd>
        </dl>
        <dl class="keyboard-mappings">
          <dt>u</dt>
          <dd>Back to issues</dd>
        </dl>
        <dl class="keyboard-mappings">
          <dt>/</dt>
          <dd>Focus issues search</dd>
        </dl>
      </div>
    </div>
  </div>

  <div style='display:none'>
    <div class="rule"></div>

    <h3>Issues Dashboard</h3>

    <div class="columns threecols">
      <div class="column first">
        <dl class="keyboard-mappings">
          <dt>j</dt>
          <dd>Move selection down</dd>
        </dl>
        <dl class="keyboard-mappings">
          <dt>k</dt>
          <dd>Move selection up</dd>
        </dl>
        <dl class="keyboard-mappings">
          <dt>o <em>or</em> enter</dt>
          <dd>Open issue</dd>
        </dl>
      </div><!-- /.column.first -->
    </div>
  </div>

  <div style='display:none'>
    <div class="rule"></div>

    <h3>Network Graph</h3>
    <div class="columns equacols">
      <div class="column first">
        <dl class="keyboard-mappings">
          <dt><span class="badmono">←</span> <em>or</em> h</dt>
          <dd>Scroll left</dd>
        </dl>
        <dl class="keyboard-mappings">
          <dt><span class="badmono">→</span> <em>or</em> l</dt>
          <dd>Scroll right</dd>
        </dl>
        <dl class="keyboard-mappings">
          <dt><span class="badmono">↑</span> <em>or</em> k</dt>
          <dd>Scroll up</dd>
        </dl>
        <dl class="keyboard-mappings">
          <dt><span class="badmono">↓</span> <em>or</em> j</dt>
          <dd>Scroll down</dd>
        </dl>
        <dl class="keyboard-mappings">
          <dt>t</dt>
          <dd>Toggle visibility of head labels</dd>
        </dl>
      </div><!-- /.column.first -->
      <div class="column last">
        <dl class="keyboard-mappings">
          <dt>shift <span class="badmono">←</span> <em>or</em> shift h</dt>
          <dd>Scroll all the way left</dd>
        </dl>
        <dl class="keyboard-mappings">
          <dt>shift <span class="badmono">→</span> <em>or</em> shift l</dt>
          <dd>Scroll all the way right</dd>
        </dl>
        <dl class="keyboard-mappings">
          <dt>shift <span class="badmono">↑</span> <em>or</em> shift k</dt>
          <dd>Scroll all the way up</dd>
        </dl>
        <dl class="keyboard-mappings">
          <dt>shift <span class="badmono">↓</span> <em>or</em> shift j</dt>
          <dd>Scroll all the way down</dd>
        </dl>
      </div><!-- /.column.last -->
    </div>
  </div>

  <div >
    <div class="rule"></div>
    <div class="columns threecols">
      <div class="column first" >
        <h3>Source Code Browsing</h3>
        <dl class="keyboard-mappings">
          <dt>t</dt>
          <dd>Activates the file finder</dd>
        </dl>
        <dl class="keyboard-mappings">
          <dt>l</dt>
          <dd>Jump to line</dd>
        </dl>
        <dl class="keyboard-mappings">
          <dt>w</dt>
          <dd>Switch branch/tag</dd>
        </dl>
        <dl class="keyboard-mappings">
          <dt>y</dt>
          <dd>Expand URL to its canonical form</dd>
        </dl>
      </div>
    </div>
  </div>

  <div style='display:none'>
    <div class="rule"></div>
    <div class="columns threecols">
      <div class="column first">
        <h3>Browsing Commits</h3>
        <dl class="keyboard-mappings">
          <dt><span class="platform-mac">⌘</span><span class="platform-other">ctrl</span> <em>+</em> enter</dt>
          <dd>Submit comment</dd>
        </dl>
        <dl class="keyboard-mappings">
          <dt>escape</dt>
          <dd>Close form</dd>
        </dl>
        <dl class="keyboard-mappings">
          <dt>p</dt>
          <dd>Parent commit</dd>
        </dl>
        <dl class="keyboard-mappings">
          <dt>o</dt>
          <dd>Other parent commit</dd>
        </dl>
      </div>
    </div>
  </div>
</div>

    <div id="markdown-help" class="instapaper_ignore readability-extra">
  <h2>Markdown Cheat Sheet</h2>

  <div class="cheatsheet-content">

  <div class="mod">
    <div class="col">
      <h3>Format Text</h3>
      <p>Headers</p>
      <pre>
# This is an &lt;h1&gt; tag
## This is an &lt;h2&gt; tag
###### This is an &lt;h6&gt; tag</pre>
     <p>Text styles</p>
     <pre>
*This text will be italic*
_This will also be italic_
**This text will be bold**
__This will also be bold__

*You **can** combine them*
</pre>
    </div>
    <div class="col">
      <h3>Lists</h3>
      <p>Unordered</p>
      <pre>
* Item 1
* Item 2
  * Item 2a
  * Item 2b</pre>
     <p>Ordered</p>
     <pre>
1. Item 1
2. Item 2
3. Item 3
   * Item 3a
   * Item 3b</pre>
    </div>
    <div class="col">
      <h3>Miscellaneous</h3>
      <p>Images</p>
      <pre>
![GitHub Logo](/images/logo.png)
Format: ![Alt Text](url)
</pre>
     <p>Links</p>
     <pre>
http://github.com - automatic!
[GitHub](http://github.com)</pre>
<p>Blockquotes</p>
     <pre>
As Kanye West said:

> We're living the future so
> the present is our past.
</pre>
    </div>
  </div>
  <div class="rule"></div>

  <h3>Code Examples in Markdown</h3>
  <div class="col">
      <p>Syntax highlighting with <a href="http://github.github.com/github-flavored-markdown/" title="GitHub Flavored Markdown" target="_blank">GFM</a></p>
      <pre>
```javascript
function fancyAlert(arg) {
  if(arg) {
    $.facebox({div:'#foo'})
  }
}
```</pre>
    </div>
    <div class="col">
      <p>Or, indent your code 4 spaces</p>
      <pre>
Here is a Python code example
without syntax highlighting:

    def foo:
      if not bar:
        return true</pre>
    </div>
    <div class="col">
      <p>Inline code for comments</p>
      <pre>
I think you should use an
`&lt;addr&gt;` element here instead.</pre>
    </div>
  </div>

  </div>
</div>


    <div id="ajax-error-message">
      <span class="mini-icon mini-icon-exclamation"></span>
      Something went wrong with that request. Please try again.
      <a href="#" class="ajax-error-dismiss">Dismiss</a>
    </div>

    <div id="logo-popup">
      <h2>Looking for the GitHub logo?</h2>
      <ul>
        <li>
          <h4>GitHub Logo</h4>
          <a href="http://github-media-downloads.s3.amazonaws.com/GitHub_Logos.zip"><img alt="Github_logo" src="https://a248.e.akamai.net/assets.github.com/images/modules/about_page/github_logo.png?1315867479" /></a>
          <a href="http://github-media-downloads.s3.amazonaws.com/GitHub_Logos.zip" class="minibutton btn-download download">Download</a>
        </li>
        <li>
          <h4>The Octocat</h4>
          <a href="http://github-media-downloads.s3.amazonaws.com/Octocats.zip"><img alt="Octocat" src="https://a248.e.akamai.net/assets.github.com/images/modules/about_page/octocat.png?1315867479" /></a>
          <a href="http://github-media-downloads.s3.amazonaws.com/Octocats.zip" class="minibutton btn-download download">Download</a>
        </li>
      </ul>
    </div>

    
    
    
    <span id='server_response_time' data-time='0.31204' data-host='fe9'></span>
  </body>
</html>

