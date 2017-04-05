import React, { Component, PropTypes } from 'react';
const propTypes = {
};
const defaultProps = {
};
class Contents extends Component {
    constructor(props) {
        super(props);
    }
    render() {
        return(
            <div>
                <section className="Article-section">
                    {this.props.children}
                </section>
            </div>
        );
    }
}
Contents.propTypes = propTypes;
Contents.defaultProps = defaultProps;
export default Contents;
