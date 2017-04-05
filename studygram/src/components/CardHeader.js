import React, { Component, PropTypes } from 'react';
{ /* import profilePic from '../profilePic.png'; */ }

const propTypes = {
    name: React.PropTypes.string,
    time: React.PropTypes.string,
};
const defaultProps = {
    name: "default name momo",
    time: "default time 1분 전"
};
class CardHeader extends Component {
    constructor(props) {
        super(props);
    }
    render() {
        return(
            <div>
                { /* <img src={profilePic} alt="profile"/> */ }
                <h3>this.props.name</h3>
                <span>this.props.time</span>
            </div>
        );
    }
}
CardHeader.propTypes = propTypes;
CardHeader.defaultProps = defaultProps;
export default CardHeader;
