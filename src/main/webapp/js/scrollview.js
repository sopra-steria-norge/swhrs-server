  


<!DOCTYPE html>
<html>
  <head prefix="og: http://ogp.me/ns# fb: http://ogp.me/ns/fb# githubog: http://ogp.me/ns/fb/githubog#">
    <meta charset='utf-8'>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>jquery-mobile/experiments/scrollview/scrollview.js at master · jquery/jquery-mobile</title>
    <link rel="search" type="application/opensearchdescription+xml" href="/opensearch.xml" title="GitHub" />
    <link rel="fluid-icon" href="https://github.com/fluidicon.png" title="GitHub" />
    <link rel="shortcut icon" href="/favicon.ico" type="image/x-icon" />

    
    

    <meta content="authenticity_token" name="csrf-param" />
<meta content="12rxZum6mgfynERI2rOCPNUiEEFKTY5mrFmVdq2nToA=" name="csrf-token" />

    <link href="https://a248.e.akamai.net/assets.github.com/stylesheets/bundles/github-66e1073376a8ca3f5a94d0e4858971a07b9f512e.css" media="screen" rel="stylesheet" type="text/css" />
    <link href="https://a248.e.akamai.net/assets.github.com/stylesheets/bundles/github2-9a6987061a89bac7e001fbfac3a4a9e99aeb9436.css" media="screen" rel="stylesheet" type="text/css" />
    
    


    <script src="https://a248.e.akamai.net/assets.github.com/javascripts/bundles/frameworks-a450c7f907bdc1ee6b362ab1ecca811c761fd259.js" type="text/javascript"></script>
    
    <script defer="defer" src="https://a248.e.akamai.net/assets.github.com/javascripts/bundles/github-14723957ec35ef409d6171b25ca647b160a7a2d2.js" type="text/javascript"></script>
    
    

      <link rel='permalink' href='/jquery/jquery-mobile/blob/33ddcd5960ab121f891db030d44013ce5fe6b02f/experiments/scrollview/scrollview.js'>
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
                      <a href="/jquery/jquery-mobile/blob/1.0-stable/experiments/scrollview/scrollview.js" class="js-navigation-open" data-name="1.0-stable" rel="nofollow">1.0-stable</a>
                  </h4>
                </div>
                <div class="commitish-item branch-commitish selector-item js-navigation-item js-navigation-target">
                  <h4>
                      <a href="/jquery/jquery-mobile/blob/1.1-stable/experiments/scrollview/scrollview.js" class="js-navigation-open" data-name="1.1-stable" rel="nofollow">1.1-stable</a>
                  </h4>
                </div>
                <div class="commitish-item branch-commitish selector-item js-navigation-item js-navigation-target">
                  <h4>
                      <a href="/jquery/jquery-mobile/blob/1.2/experiments/scrollview/scrollview.js" class="js-navigation-open" data-name="1.2" rel="nofollow">1.2</a>
                  </h4>
                </div>
                <div class="commitish-item branch-commitish selector-item js-navigation-item js-navigation-target">
                  <h4>
                      <a href="/jquery/jquery-mobile/blob/changepage-prevent/experiments/scrollview/scrollview.js" class="js-navigation-open" data-name="changepage-prevent" rel="nofollow">changepage-prevent</a>
                  </h4>
                </div>
                <div class="commitish-item branch-commitish selector-item js-navigation-item js-navigation-target">
                  <h4>
                      <a href="/jquery/jquery-mobile/blob/cssstructure/experiments/scrollview/scrollview.js" class="js-navigation-open" data-name="cssstructure" rel="nofollow">cssstructure</a>
                  </h4>
                </div>
                <div class="commitish-item branch-commitish selector-item js-navigation-item js-navigation-target">
                  <h4>
                      <a href="/jquery/jquery-mobile/blob/custom-select-via-popup/experiments/scrollview/scrollview.js" class="js-navigation-open" data-name="custom-select-via-popup" rel="nofollow">custom-select-via-popup</a>
                  </h4>
                </div>
                <div class="commitish-item branch-commitish selector-item js-navigation-item js-navigation-target">
                  <h4>
                      <a href="/jquery/jquery-mobile/blob/data-driven-with-fallback/experiments/scrollview/scrollview.js" class="js-navigation-open" data-name="data-driven-with-fallback" rel="nofollow">data-driven-with-fallback</a>
                  </h4>
                </div>
                <div class="commitish-item branch-commitish selector-item js-navigation-item js-navigation-target">
                  <h4>
                      <a href="/jquery/jquery-mobile/blob/fetchlink/experiments/scrollview/scrollview.js" class="js-navigation-open" data-name="fetchlink" rel="nofollow">fetchlink</a>
                  </h4>
                </div>
                <div class="commitish-item branch-commitish selector-item js-navigation-item js-navigation-target">
                  <h4>
                      <a href="/jquery/jquery-mobile/blob/fix-4423/experiments/scrollview/scrollview.js" class="js-navigation-open" data-name="fix-4423" rel="nofollow">fix-4423</a>
                  </h4>
                </div>
                <div class="commitish-item branch-commitish selector-item js-navigation-item js-navigation-target">
                  <h4>
                      <a href="/jquery/jquery-mobile/blob/fixedtoolbar-polyfill/experiments/scrollview/scrollview.js" class="js-navigation-open" data-name="fixedtoolbar-polyfill" rel="nofollow">fixedtoolbar-polyfill</a>
                  </h4>
                </div>
                <div class="commitish-item branch-commitish selector-item js-navigation-item js-navigation-target">
                  <h4>
                      <a href="/jquery/jquery-mobile/blob/grunt/experiments/scrollview/scrollview.js" class="js-navigation-open" data-name="grunt" rel="nofollow">grunt</a>
                  </h4>
                </div>
                <div class="commitish-item branch-commitish selector-item js-navigation-item js-navigation-target">
                  <h4>
                      <a href="/jquery/jquery-mobile/blob/list-perf/experiments/scrollview/scrollview.js" class="js-navigation-open" data-name="list-perf" rel="nofollow">list-perf</a>
                  </h4>
                </div>
                <div class="commitish-item branch-commitish selector-item js-navigation-item js-navigation-target">
                  <h4>
                      <a href="/jquery/jquery-mobile/blob/master/experiments/scrollview/scrollview.js" class="js-navigation-open" data-name="master" rel="nofollow">master</a>
                  </h4>
                </div>
                <div class="commitish-item branch-commitish selector-item js-navigation-item js-navigation-target">
                  <h4>
                      <a href="/jquery/jquery-mobile/blob/nav-ready-deferred/experiments/scrollview/scrollview.js" class="js-navigation-open" data-name="nav-ready-deferred" rel="nofollow">nav-ready-deferred</a>
                  </h4>
                </div>
                <div class="commitish-item branch-commitish selector-item js-navigation-item js-navigation-target">
                  <h4>
                      <a href="/jquery/jquery-mobile/blob/new-readme/experiments/scrollview/scrollview.js" class="js-navigation-open" data-name="new-readme" rel="nofollow">new-readme</a>
                  </h4>
                </div>
                <div class="commitish-item branch-commitish selector-item js-navigation-item js-navigation-target">
                  <h4>
                      <a href="/jquery/jquery-mobile/blob/popup-widget/experiments/scrollview/scrollview.js" class="js-navigation-open" data-name="popup-widget" rel="nofollow">popup-widget</a>
                  </h4>
                </div>
                <div class="commitish-item branch-commitish selector-item js-navigation-item js-navigation-target">
                  <h4>
                      <a href="/jquery/jquery-mobile/blob/toolbar-persist/experiments/scrollview/scrollview.js" class="js-navigation-open" data-name="toolbar-persist" rel="nofollow">toolbar-persist</a>
                  </h4>
                </div>
                <div class="commitish-item branch-commitish selector-item js-navigation-item js-navigation-target">
                  <h4>
                      <a href="/jquery/jquery-mobile/blob/transitions-sequential-simultaneous/experiments/scrollview/scrollview.js" class="js-navigation-open" data-name="transitions-sequential-simultaneous" rel="nofollow">transitions-sequential-simultaneous</a>
                  </h4>
                </div>
                <div class="commitish-item branch-commitish selector-item js-navigation-item js-navigation-target">
                  <h4>
                      <a href="/jquery/jquery-mobile/blob/ui-tabs-experiment/experiments/scrollview/scrollview.js" class="js-navigation-open" data-name="ui-tabs-experiment" rel="nofollow">ui-tabs-experiment</a>
                  </h4>
                </div>
            </div>

            <div class="js-filter-tab js-filter-tags" style="display:none" data-filterable-for="context-commitish-filter-field" data-filterable-type=substring>
              <div class="no-results js-not-filterable">Nothing to show</div>
                <div class="commitish-item tag-commitish selector-item js-navigation-item js-navigation-target">
                  <h4>
                      <a href="/jquery/jquery-mobile/blob/1.2.0-pre/experiments/scrollview/scrollview.js" class="js-navigation-open" data-name="1.2.0-pre" rel="nofollow">1.2.0-pre</a>
                  </h4>
                </div>
                <div class="commitish-item tag-commitish selector-item js-navigation-item js-navigation-target">
                  <h4>
                      <a href="/jquery/jquery-mobile/blob/1.1.1-rc.1/experiments/scrollview/scrollview.js" class="js-navigation-open" data-name="1.1.1-rc.1" rel="nofollow">1.1.1-rc.1</a>
                  </h4>
                </div>
                <div class="commitish-item tag-commitish selector-item js-navigation-item js-navigation-target">
                  <h4>
                      <a href="/jquery/jquery-mobile/blob/1.1.0-rc.2/experiments/scrollview/scrollview.js" class="js-navigation-open" data-name="1.1.0-rc.2" rel="nofollow">1.1.0-rc.2</a>
                  </h4>
                </div>
                <div class="commitish-item tag-commitish selector-item js-navigation-item js-navigation-target">
                  <h4>
                      <a href="/jquery/jquery-mobile/blob/1.1.0-rc.1/experiments/scrollview/scrollview.js" class="js-navigation-open" data-name="1.1.0-rc.1" rel="nofollow">1.1.0-rc.1</a>
                  </h4>
                </div>
                <div class="commitish-item tag-commitish selector-item js-navigation-item js-navigation-target">
                  <h4>
                      <a href="/jquery/jquery-mobile/blob/1.1.0/experiments/scrollview/scrollview.js" class="js-navigation-open" data-name="1.1.0" rel="nofollow">1.1.0</a>
                  </h4>
                </div>
                <div class="commitish-item tag-commitish selector-item js-navigation-item js-navigation-target">
                  <h4>
                      <a href="/jquery/jquery-mobile/blob/1.0rc3/experiments/scrollview/scrollview.js" class="js-navigation-open" data-name="1.0rc3" rel="nofollow">1.0rc3</a>
                  </h4>
                </div>
                <div class="commitish-item tag-commitish selector-item js-navigation-item js-navigation-target">
                  <h4>
                      <a href="/jquery/jquery-mobile/blob/1.0rc2/experiments/scrollview/scrollview.js" class="js-navigation-open" data-name="1.0rc2" rel="nofollow">1.0rc2</a>
                  </h4>
                </div>
                <div class="commitish-item tag-commitish selector-item js-navigation-item js-navigation-target">
                  <h4>
                      <a href="/jquery/jquery-mobile/blob/1.0rc1/experiments/scrollview/scrollview.js" class="js-navigation-open" data-name="1.0rc1" rel="nofollow">1.0rc1</a>
                  </h4>
                </div>
                <div class="commitish-item tag-commitish selector-item js-navigation-item js-navigation-target">
                  <h4>
                      <a href="/jquery/jquery-mobile/blob/1.0b3/experiments/scrollview/scrollview.js" class="js-navigation-open" data-name="1.0b3" rel="nofollow">1.0b3</a>
                  </h4>
                </div>
                <div class="commitish-item tag-commitish selector-item js-navigation-item js-navigation-target">
                  <h4>
                      <a href="/jquery/jquery-mobile/blob/1.0b2/experiments/scrollview/scrollview.js" class="js-navigation-open" data-name="1.0b2" rel="nofollow">1.0b2</a>
                  </h4>
                </div>
                <div class="commitish-item tag-commitish selector-item js-navigation-item js-navigation-target">
                  <h4>
                      <a href="/jquery/jquery-mobile/blob/1.0b1/experiments/scrollview/scrollview.js" class="js-navigation-open" data-name="1.0b1" rel="nofollow">1.0b1</a>
                  </h4>
                </div>
                <div class="commitish-item tag-commitish selector-item js-navigation-item js-navigation-target">
                  <h4>
                      <a href="/jquery/jquery-mobile/blob/1.0a4.1/experiments/scrollview/scrollview.js" class="js-navigation-open" data-name="1.0a4.1" rel="nofollow">1.0a4.1</a>
                  </h4>
                </div>
                <div class="commitish-item tag-commitish selector-item js-navigation-item js-navigation-target">
                  <h4>
                      <a href="/jquery/jquery-mobile/blob/1.0a4/experiments/scrollview/scrollview.js" class="js-navigation-open" data-name="1.0a4" rel="nofollow">1.0a4</a>
                  </h4>
                </div>
                <div class="commitish-item tag-commitish selector-item js-navigation-item js-navigation-target">
                  <h4>
                      <a href="/jquery/jquery-mobile/blob/1.0a3/experiments/scrollview/scrollview.js" class="js-navigation-open" data-name="1.0a3" rel="nofollow">1.0a3</a>
                  </h4>
                </div>
                <div class="commitish-item tag-commitish selector-item js-navigation-item js-navigation-target">
                  <h4>
                      <a href="/jquery/jquery-mobile/blob/1.0a2/experiments/scrollview/scrollview.js" class="js-navigation-open" data-name="1.0a2" rel="nofollow">1.0a2</a>
                  </h4>
                </div>
                <div class="commitish-item tag-commitish selector-item js-navigation-item js-navigation-target">
                  <h4>
                      <a href="/jquery/jquery-mobile/blob/1.0a1/experiments/scrollview/scrollview.js" class="js-navigation-open" data-name="1.0a1" rel="nofollow">1.0a1</a>
                  </h4>
                </div>
                <div class="commitish-item tag-commitish selector-item js-navigation-item js-navigation-target">
                  <h4>
                      <a href="/jquery/jquery-mobile/blob/1.0.1/experiments/scrollview/scrollview.js" class="js-navigation-open" data-name="1.0.1" rel="nofollow">1.0.1</a>
                  </h4>
                </div>
                <div class="commitish-item tag-commitish selector-item js-navigation-item js-navigation-target">
                  <h4>
                      <a href="/jquery/jquery-mobile/blob/1.0/experiments/scrollview/scrollview.js" class="js-navigation-open" data-name="1.0" rel="nofollow">1.0</a>
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

        





<!-- block_view_fragment_key: views10/v8/blob:v21:ea2b1f83c56a63d653ad254ef5c818d3 -->
  <div id="slider">

    <div class="breadcrumb" data-path="experiments/scrollview/scrollview.js/">
      <b itemscope="" itemtype="http://data-vocabulary.org/Breadcrumb"><a href="/jquery/jquery-mobile/tree/7fdde085b52d46c050f1b3ac0dae20ea825f7dea" class="js-rewrite-sha" itemprop="url"><span itemprop="title">jquery-mobile</span></a></b> / <span itemscope="" itemtype="http://data-vocabulary.org/Breadcrumb"><a href="/jquery/jquery-mobile/tree/7fdde085b52d46c050f1b3ac0dae20ea825f7dea/experiments" class="js-rewrite-sha" itemscope="url"><span itemprop="title">experiments</span></a></span> / <span itemscope="" itemtype="http://data-vocabulary.org/Breadcrumb"><a href="/jquery/jquery-mobile/tree/7fdde085b52d46c050f1b3ac0dae20ea825f7dea/experiments/scrollview" class="js-rewrite-sha" itemscope="url"><span itemprop="title">scrollview</span></a></span> / <strong class="final-path">scrollview.js</strong> <span class="js-clippy mini-icon mini-icon-clippy " data-clipboard-text="experiments/scrollview/scrollview.js" data-copied-hint="copied!" data-copy-hint="copy to clipboard"></span>
    </div>


      <div class="commit file-history-tease" data-path="experiments/scrollview/scrollview.js/">
        <img class="main-avatar" height="24" src="https://secure.gravatar.com/avatar/08a01ffbfa6e039295208f023dec0dae?s=140&amp;d=https://a248.e.akamai.net/assets.github.com%2Fimages%2Fgravatars%2Fgravatar-140.png" width="24" />
        <span class="author"><a href="/eddiemonge">eddiemonge</a></span>
        <time class="js-relative-date" datetime="2011-11-07T18:14:29-08:00" title="2011-11-07 18:14:29">November 07, 2011</time>
        <div class="commit-title">
            <a href="/jquery/jquery-mobile/commit/4f354bafbcdda07ab119d443f8737a76408770c0" class="message">Wrong OR operator to compare values</a>
        </div>

        <div class="participation">
          <p class="quickstat"><a href="#blob_contributors_box" rel="facebox"><strong>4</strong> contributors</a></p>
              <a class="avatar tooltipped downwards" title="jblas" href="/jquery/jquery-mobile/commits/master/experiments/scrollview/scrollview.js?author=jblas"><img height="20" src="https://secure.gravatar.com/avatar/fa72320f7fd01aa7d743768f85ccf9ea?s=140&amp;d=https://a248.e.akamai.net/assets.github.com%2Fimages%2Fgravatars%2Fgravatar-140.png" width="20" /></a>
    <a class="avatar tooltipped downwards" title="eddiemonge" href="/jquery/jquery-mobile/commits/master/experiments/scrollview/scrollview.js?author=eddiemonge"><img height="20" src="https://secure.gravatar.com/avatar/08a01ffbfa6e039295208f023dec0dae?s=140&amp;d=https://a248.e.akamai.net/assets.github.com%2Fimages%2Fgravatars%2Fgravatar-140.png" width="20" /></a>
    <a class="avatar tooltipped downwards" title="scottjehl" href="/jquery/jquery-mobile/commits/master/experiments/scrollview/scrollview.js?author=scottjehl"><img height="20" src="https://secure.gravatar.com/avatar/4137f7daffde77fce06a26a1ac92f9bf?s=140&amp;d=https://a248.e.akamai.net/assets.github.com%2Fimages%2Fgravatars%2Fgravatar-140.png" width="20" /></a>
    <a class="avatar tooltipped downwards" title="bestform" href="/jquery/jquery-mobile/commits/master/experiments/scrollview/scrollview.js?author=bestform"><img height="20" src="https://secure.gravatar.com/avatar/3a0c6dc105bd8e6e2cb52839eadc6917?s=140&amp;d=https://a248.e.akamai.net/assets.github.com%2Fimages%2Fgravatars%2Fgravatar-140.png" width="20" /></a>


        </div>
        <div id="blob_contributors_box" style="display:none">
          <h2>Users on GitHub who have contributed to this file</h2>
          <ul class="facebox-user-list">
            <li>
              <img height="24" src="https://secure.gravatar.com/avatar/fa72320f7fd01aa7d743768f85ccf9ea?s=140&amp;d=https://a248.e.akamai.net/assets.github.com%2Fimages%2Fgravatars%2Fgravatar-140.png" width="24" />
              <a href="/jblas">jblas</a>
            </li>
            <li>
              <img height="24" src="https://secure.gravatar.com/avatar/08a01ffbfa6e039295208f023dec0dae?s=140&amp;d=https://a248.e.akamai.net/assets.github.com%2Fimages%2Fgravatars%2Fgravatar-140.png" width="24" />
              <a href="/eddiemonge">eddiemonge</a>
            </li>
            <li>
              <img height="24" src="https://secure.gravatar.com/avatar/4137f7daffde77fce06a26a1ac92f9bf?s=140&amp;d=https://a248.e.akamai.net/assets.github.com%2Fimages%2Fgravatars%2Fgravatar-140.png" width="24" />
              <a href="/scottjehl">scottjehl</a>
            </li>
            <li>
              <img height="24" src="https://secure.gravatar.com/avatar/3a0c6dc105bd8e6e2cb52839eadc6917?s=140&amp;d=https://a248.e.akamai.net/assets.github.com%2Fimages%2Fgravatars%2Fgravatar-140.png" width="24" />
              <a href="/bestform">bestform</a>
            </li>
          </ul>
        </div>
      </div>

    <div class="frames">
      <div class="frame frame-center" data-path="experiments/scrollview/scrollview.js/" data-permalink-url="/jquery/jquery-mobile/blob/7fdde085b52d46c050f1b3ac0dae20ea825f7dea/experiments/scrollview/scrollview.js" data-title="jquery-mobile/experiments/scrollview/scrollview.js at master · jquery/jquery-mobile · GitHub" data-type="blob">

        <div id="files" class="bubble">
          <div class="file">
            <div class="meta">
              <div class="info">
                <span class="icon"><b class="mini-icon mini-icon-text-file"></b></span>
                <span class="mode" title="File Mode">100644</span>
                  <span>55 lines (44 sloc)</span>
                <span>1.91 kb</span>
              </div>
              <ul class="button-group actions">
                  <li>
                    <a class="grouped-button file-edit-link minibutton bigger lighter js-rewrite-sha" href="/jquery/jquery-mobile/edit/7fdde085b52d46c050f1b3ac0dae20ea825f7dea/experiments/scrollview/scrollview.js" data-method="post" rel="nofollow" data-hotkey="e">Edit this file</a>
                  </li>

                <li>
                  <a href="/jquery/jquery-mobile/raw/master/experiments/scrollview/scrollview.js" class="minibutton btn-raw grouped-button bigger lighter" id="raw-url"><span class="icon"></span>Raw</a>
                </li>
                  <li>
                    <a href="/jquery/jquery-mobile/blame/master/experiments/scrollview/scrollview.js" class="minibutton btn-blame grouped-button bigger lighter"><span class="icon"></span>Blame</a>
                  </li>
                <li>
                  <a href="/jquery/jquery-mobile/commits/master/experiments/scrollview/scrollview.js" class="minibutton btn-history grouped-button bigger lighter" rel="nofollow"><span class="icon"></span>History</a>
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
</pre>
          </td>
          <td width="100%">
                <div class="highlight"><pre><div class='line' id='LC1'><span class="kd">function</span> <span class="nx">ResizePageContentHeight</span><span class="p">(</span><span class="nx">page</span><span class="p">)</span> <span class="p">{</span></div><div class='line' id='LC2'>	<span class="kd">var</span> <span class="nx">$page</span> <span class="o">=</span> <span class="nx">$</span><span class="p">(</span><span class="nx">page</span><span class="p">),</span></div><div class='line' id='LC3'>		<span class="nx">$content</span> <span class="o">=</span> <span class="nx">$page</span><span class="p">.</span><span class="nx">children</span><span class="p">(</span> <span class="s2">&quot;.ui-content&quot;</span> <span class="p">),</span></div><div class='line' id='LC4'>		<span class="nx">hh</span> <span class="o">=</span> <span class="nx">$page</span><span class="p">.</span><span class="nx">children</span><span class="p">(</span> <span class="s2">&quot;.ui-header&quot;</span> <span class="p">).</span><span class="nx">outerHeight</span><span class="p">()</span> <span class="o">||</span> <span class="mi">0</span><span class="p">,</span></div><div class='line' id='LC5'>		<span class="nx">fh</span> <span class="o">=</span> <span class="nx">$page</span><span class="p">.</span><span class="nx">children</span><span class="p">(</span> <span class="s2">&quot;.ui-footer&quot;</span> <span class="p">).</span><span class="nx">outerHeight</span><span class="p">()</span> <span class="o">||</span> <span class="mi">0</span><span class="p">,</span></div><div class='line' id='LC6'>		<span class="nx">pt</span> <span class="o">=</span> <span class="nb">parseFloat</span><span class="p">(</span><span class="nx">$content</span><span class="p">.</span><span class="nx">css</span><span class="p">(</span> <span class="s2">&quot;padding-top&quot;</span> <span class="p">)),</span></div><div class='line' id='LC7'>		<span class="nx">pb</span> <span class="o">=</span> <span class="nb">parseFloat</span><span class="p">(</span><span class="nx">$content</span><span class="p">.</span><span class="nx">css</span><span class="p">(</span> <span class="s2">&quot;padding-bottom&quot;</span> <span class="p">)),</span></div><div class='line' id='LC8'>		<span class="nx">wh</span> <span class="o">=</span> <span class="nb">window</span><span class="p">.</span><span class="nx">innerHeight</span><span class="p">;</span></div><div class='line' id='LC9'><br/></div><div class='line' id='LC10'>	<span class="nx">$content</span><span class="p">.</span><span class="nx">height</span><span class="p">(</span><span class="nx">wh</span> <span class="o">-</span> <span class="p">(</span><span class="nx">hh</span> <span class="o">+</span> <span class="nx">fh</span><span class="p">)</span> <span class="o">-</span> <span class="p">(</span><span class="nx">pt</span> <span class="o">+</span> <span class="nx">pb</span><span class="p">));</span></div><div class='line' id='LC11'><span class="p">}</span></div><div class='line' id='LC12'><br/></div><div class='line' id='LC13'><span class="nx">$</span><span class="p">(</span> <span class="s2">&quot;:jqmData(role=&#39;page&#39;)&quot;</span> <span class="p">).</span><span class="nx">live</span><span class="p">(</span> <span class="s2">&quot;pageshow&quot;</span><span class="p">,</span> <span class="kd">function</span><span class="p">(</span><span class="nx">event</span><span class="p">)</span> <span class="p">{</span></div><div class='line' id='LC14'>	<span class="kd">var</span> <span class="nx">$page</span> <span class="o">=</span> <span class="nx">$</span><span class="p">(</span> <span class="k">this</span> <span class="p">);</span></div><div class='line' id='LC15'><br/></div><div class='line' id='LC16'>	<span class="c1">// For the demos that use this script, we want the content area of each</span></div><div class='line' id='LC17'>	<span class="c1">// page to be scrollable in the &#39;y&#39; direction.</span></div><div class='line' id='LC18'><br/></div><div class='line' id='LC19'>	<span class="nx">$page</span><span class="p">.</span><span class="nx">find</span><span class="p">(</span> <span class="s2">&quot;.ui-content&quot;</span> <span class="p">).</span><span class="nx">attr</span><span class="p">(</span> <span class="s2">&quot;data-&quot;</span> <span class="o">+</span> <span class="nx">$</span><span class="p">.</span><span class="nx">mobile</span><span class="p">.</span><span class="nx">ns</span> <span class="o">+</span> <span class="s2">&quot;scroll&quot;</span><span class="p">,</span> <span class="s2">&quot;y&quot;</span> <span class="p">);</span></div><div class='line' id='LC20'><br/></div><div class='line' id='LC21'>	<span class="c1">// This code that looks for [data-scroll] will eventually be folded</span></div><div class='line' id='LC22'>	<span class="c1">// into the jqm page processing code when scrollview support is &quot;official&quot;</span></div><div class='line' id='LC23'>	<span class="c1">// instead of &quot;experimental&quot;.</span></div><div class='line' id='LC24'><br/></div><div class='line' id='LC25'>	<span class="nx">$page</span><span class="p">.</span><span class="nx">find</span><span class="p">(</span> <span class="s2">&quot;:jqmData(scroll):not(.ui-scrollview-clip)&quot;</span> <span class="p">).</span><span class="nx">each</span><span class="p">(</span><span class="kd">function</span> <span class="p">()</span> <span class="p">{</span></div><div class='line' id='LC26'>		<span class="kd">var</span> <span class="nx">$this</span> <span class="o">=</span> <span class="nx">$</span><span class="p">(</span> <span class="k">this</span> <span class="p">);</span></div><div class='line' id='LC27'>		<span class="c1">// XXX: Remove this check for ui-scrolllistview once we&#39;ve</span></div><div class='line' id='LC28'>		<span class="c1">//      integrated list divider support into the main scrollview class.</span></div><div class='line' id='LC29'>		<span class="k">if</span> <span class="p">(</span> <span class="nx">$this</span><span class="p">.</span><span class="nx">hasClass</span><span class="p">(</span> <span class="s2">&quot;ui-scrolllistview&quot;</span> <span class="p">)</span> <span class="p">)</span> <span class="p">{</span></div><div class='line' id='LC30'>			<span class="nx">$this</span><span class="p">.</span><span class="nx">scrolllistview</span><span class="p">();</span></div><div class='line' id='LC31'>		<span class="p">}</span> <span class="k">else</span> <span class="p">{</span></div><div class='line' id='LC32'>			<span class="kd">var</span> <span class="nx">st</span> <span class="o">=</span> <span class="nx">$this</span><span class="p">.</span><span class="nx">jqmData</span><span class="p">(</span> <span class="s2">&quot;scroll&quot;</span> <span class="p">)</span> <span class="o">+</span> <span class="s2">&quot;&quot;</span><span class="p">,</span></div><div class='line' id='LC33'>				<span class="nx">paging</span> <span class="o">=</span> <span class="nx">st</span> <span class="o">&amp;&amp;</span> <span class="nx">st</span><span class="p">.</span><span class="nx">search</span><span class="p">(</span><span class="sr">/^[xy]p$/</span><span class="p">)</span> <span class="o">!=</span> <span class="o">-</span><span class="mi">1</span><span class="p">,</span></div><div class='line' id='LC34'>				<span class="nx">dir</span> <span class="o">=</span> <span class="nx">st</span> <span class="o">&amp;&amp;</span> <span class="nx">st</span><span class="p">.</span><span class="nx">search</span><span class="p">(</span><span class="sr">/^[xy]/</span><span class="p">)</span> <span class="o">!=</span> <span class="o">-</span><span class="mi">1</span> <span class="o">?</span> <span class="nx">st</span><span class="p">.</span><span class="nx">charAt</span><span class="p">(</span><span class="mi">0</span><span class="p">)</span> <span class="o">:</span> <span class="kc">null</span><span class="p">,</span></div><div class='line' id='LC35'><br/></div><div class='line' id='LC36'>				<span class="nx">opts</span> <span class="o">=</span> <span class="p">{</span></div><div class='line' id='LC37'>					<span class="nx">direction</span><span class="o">:</span> <span class="nx">dir</span> <span class="o">||</span> <span class="kc">undefined</span><span class="p">,</span></div><div class='line' id='LC38'>					<span class="nx">paging</span><span class="o">:</span> <span class="nx">paging</span> <span class="o">||</span> <span class="kc">undefined</span><span class="p">,</span></div><div class='line' id='LC39'>					<span class="nx">scrollMethod</span><span class="o">:</span> <span class="nx">$this</span><span class="p">.</span><span class="nx">jqmData</span><span class="p">(</span><span class="s2">&quot;scroll-method&quot;</span><span class="p">)</span> <span class="o">||</span> <span class="kc">undefined</span></div><div class='line' id='LC40'>				<span class="p">};</span></div><div class='line' id='LC41'><br/></div><div class='line' id='LC42'>			<span class="nx">$this</span><span class="p">.</span><span class="nx">scrollview</span><span class="p">(</span> <span class="nx">opts</span> <span class="p">);</span></div><div class='line' id='LC43'>		<span class="p">}</span></div><div class='line' id='LC44'>	<span class="p">});</span></div><div class='line' id='LC45'><br/></div><div class='line' id='LC46'>	<span class="c1">// For the demos, we want to make sure the page being shown has a content</span></div><div class='line' id='LC47'>	<span class="c1">// area that is sized to fit completely within the viewport. This should</span></div><div class='line' id='LC48'>	<span class="c1">// also handle the case where pages are loaded dynamically.</span></div><div class='line' id='LC49'><br/></div><div class='line' id='LC50'>	<span class="nx">ResizePageContentHeight</span><span class="p">(</span> <span class="nx">event</span><span class="p">.</span><span class="nx">target</span> <span class="p">);</span></div><div class='line' id='LC51'><span class="p">});</span></div><div class='line' id='LC52'><br/></div><div class='line' id='LC53'><span class="nx">$</span><span class="p">(</span> <span class="nb">window</span> <span class="p">).</span><span class="nx">bind</span><span class="p">(</span> <span class="s2">&quot;orientationchange&quot;</span><span class="p">,</span> <span class="kd">function</span><span class="p">(</span> <span class="nx">event</span> <span class="p">)</span> <span class="p">{</span></div><div class='line' id='LC54'>	<span class="nx">ResizePageContentHeight</span><span class="p">(</span> <span class="nx">$</span><span class="p">(</span> <span class="s2">&quot;.ui-page&quot;</span> <span class="p">)</span> <span class="p">);</span></div><div class='line' id='LC55'><span class="p">});</span></div></pre></div>
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

      <p>&copy; 2012 <span title="0.17818s from fe9.rs.github.com">GitHub</span> Inc. All rights reserved.</p>
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

    
    
    
    <span id='server_response_time' data-time='0.18118' data-host='fe9'></span>
  </body>
</html>

