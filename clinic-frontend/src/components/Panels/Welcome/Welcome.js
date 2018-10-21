import React, { Component }  from 'react';
import "./Welcome.css"

class Welcome extends Component{

    render () {
        var changePanel = this.props.changePanel;
        return (
            <header className="App-header">
                <svg id="Logo" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 263 263">
                  <g id="Flower">
                    <g id="Fourth">
                      <path className="st2" d="M111.718,141.571"/>
                      <path className="st2" d="M115.586,131.752"/>
                      <path className="st2" d="M107.569,145.407c-6.177,4.546-15.379,7.427-25.657,7.423c-18.593-0.007-33.657-21.105-33.657-21.105
                                           s15.081-21.086,33.673-21.078c10.986,0.004,20.742,3.305,26.885,8.408"/>
                      <path className="st2" d="M111.718,141.571"/>
                      <path className="st2" d="M131.422,164.175c-0.273,6.591-2.444,14.028-6.553,21.139c-9.303,16.098-35.106,18.596-35.106,18.596
                                           s-10.72-23.603-1.418-39.701c5.748-9.947,13.946-16.927,21.75-19.433"/>
                      <path className="st2" d="M153.493,145.158c7.575,2.707,15.429,9.562,20.987,19.196c9.29,16.105-1.449,39.7-1.449,39.7
                                           s-25.801-2.517-35.091-18.623c-4.127-7.154-6.292-14.637-6.54-21.255"/>
                      <path className="st2" d="M151.004,122.564c5.527-6.914,16.944-11.659,30.129-11.654c18.593,0.007,33.657,21.105,33.657,21.105
                                           s-15.081,21.086-33.673,21.078c-13.128-0.005-24.498-4.717-30.047-11.587"/>
                      <path className="st2" d="M141.548,119.056"/>
                      <path className="st2" d="M131.606,100.051c0.184-6.71,2.359-14.339,6.569-21.624c9.303-16.098,35.106-18.596,35.106-18.596
                                           s10.72,23.603,1.418,39.701c-5.593,9.679-13.505,16.548-21.118,19.221"/>
                      <path className="st2" d="M131.068,106.822"/>
                      <path className="st2" d="M109.356,118.511c-7.514-2.755-15.281-9.574-20.79-19.125c-9.29-16.105,1.449-39.7,1.449-39.7
                                           s25.801,2.517,35.091,18.623c4.228,7.33,6.398,15.006,6.555,21.743"/>
                    </g>
                    <g id="Third">
                      <path className="st2" d="M150.387,176.531"/>
                      <path className="st2" d="M131.416,146.225"/>
                      <path className="st2" d="M150.387,176.531c-0.007,16.733-18.994,30.291-18.994,30.291s-18.977-13.573-18.971-30.306"/>
                      <path className="st2" d="M179.622,137.87"/>
                      <path className="st2" d="M143.891,139.146"/>
                      <path className="st2" d="M160.627,170.741"/>
                      <path className="st2" d="M179.622,137.87c14.488,8.372,16.736,31.595,16.736,31.595s-21.243,9.648-35.731,1.276"/>
                      <path className="st2" d="M179.728,126.106"/>
                      <path className="st2" d="M160.758,93.221c14.495-8.361,35.73,1.304,35.73,1.304s-2.266,23.221-16.761,31.582"/>
                      <path className="st2" d="M112.659,87.233c0.007-16.733,18.994-30.291,18.994-30.291s18.977,13.573,18.971,30.306"/>
                      <path className="st2" d="M83.423,125.894c-14.488-8.372-16.736-31.595-16.736-31.595s21.243-9.648,35.731-1.276"/>
                      <path className="st2" d="M83.423,125.894"/>
                      <path className="st2" d="M102.287,170.543c-14.495,8.361-35.73-1.304-35.73-1.304s2.266-23.221,16.761-31.582"/>
                      <path className="st2" d="M119.048,138.961"/>
                      <path className="st2" d="M83.318,137.658"/>
                    </g>
                    <g id="Second">
                      <path className="st2" d="M113.835,142.046c-3.819,2.093-8.82,3.361-14.293,3.359C87.556,145.4,77.845,131.8,77.845,131.8
                                           s9.722-13.593,21.707-13.588c5.911,0.002,11.269,1.485,15.182,3.888"/>
                      <path className="st2" d="M129.24,162.283"/>
                      <path className="st2" d="M129.24,162.283c-0.562,1.36-1.231,2.72-2.006,4.063c-5.997,10.378-22.631,11.987-22.631,11.987
                                           s-6.911-15.215-0.914-25.593c2.754-4.765,6.379-8.474,10.125-10.728"/>
                      <path className="st2" d="M148.864,141.959c3.827,2.242,7.543,6.008,10.351,10.875c5.989,10.382-0.934,25.592-0.934,25.592
                                           s-16.632-1.623-22.621-12.005c-0.749-1.298-1.397-2.612-1.946-3.927"/>
                      <path className="st2" d="M148.69,122.035c3.879-2.269,9.088-3.656,14.814-3.654c11.986,0.005,21.697,13.605,21.697,13.605
                                           s-9.722,13.593-21.707,13.588c-5.639-0.002-10.775-1.352-14.633-3.562"/>
                      <path className="st2" d="M133.678,101.817c0.588-1.464,1.299-2.93,2.134-4.375c5.997-10.378,22.631-11.987,22.631-11.987
                                           s6.911,15.216,0.914,25.593c-2.88,4.984-6.715,8.813-10.642,11.03"/>
                      <path className="st2" d="M124.471,123.794"/>
                      <path className="st2" d="M114.72,122.133c-4.015-2.196-7.951-6.085-10.889-11.18c-5.989-10.382,0.934-25.592,0.934-25.592
                                           s16.632,1.623,22.621,12.005c0.851,1.475,1.573,2.972,2.166,4.466"/>
                    </g>
                    <g id="First">
                      <path className="st2" d="M122.487,123.081c-0.643-2.114-1.004-4.487-1.004-6.995c0-8.854,10.044-16.031,10.044-16.031
                                           s0.884,0.632,2.126,1.751c2.947,2.655,7.915,8.053,7.917,14.28c0.001,2.971-0.508,5.758-1.396,8.148
                                           c-1.749,4.71-4.967,7.879-8.647,7.883"/>
                      <path className="st2" d="M122.649,141.108c-2.153,0.674-4.582,1.053-7.153,1.053c-8.854,0-16.031-10.044-16.031-10.044
                                           s7.177-10.044,16.031-10.044c2.564,0,4.987,0.377,7.137,1.048c0.892,0.278,1.738,0.607,2.526,0.981
                                           c3.304,1.566,5.61,3.915,6.212,6.607c0.086,0.384,0.137,0.776,0.152,1.173c0.003,0.078,0.004,0.157,0.004,0.235
                                           c0,0.101-0.002,0.202-0.007,0.303"/>
                      <path className="st2" d="M140.523,141.013c0.67,2.149,1.047,4.572,1.047,7.136c0,8.854-10.044,16.031-10.044,16.031
                                           s-10.044-7.177-10.044-16.031s4.497-16.031,10.044-16.031c0.126,0,0.252,0.004,0.376,0.011"/>
                      <path className="st2" d="M140.564,123.077c2.114-0.643,4.486-1.004,6.994-1.004c8.854,0,16.031,10.044,16.031,10.044
                                           s-5.495,7.69-12.8,9.612c-1.044,0.275-2.124,0.432-3.231,0.432c-2.507,0-4.88-0.361-6.994-1.004
                                           c-5.349-1.628-9.037-5.064-9.037-9.04"/>
                      <path className="st2" d="M141.571,116.086c0.002,8.825-4.496,16.026-10.044,16.031"/>
                    </g>
                  </g>
                </svg>
                <h1 className="welcome-title">
                    Clinic Manager
                </h1>
                <button className="start-btn" onClick={() => changePanel("home")}>START</button>
            </header>
        );
    }
}

export default Welcome;